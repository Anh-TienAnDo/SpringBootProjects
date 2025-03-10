package com.ptit.graduation.service.product.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.WriteModel;
import com.ptit.graduation.dto.request.product.ProductFilterRequest;
import com.ptit.graduation.dto.response.product.ProductPageResponse;
import com.ptit.graduation.dto.response.product.ProductResponse;
import com.ptit.graduation.entity.product.LocationMongo;
import com.ptit.graduation.entity.product.ProductMongo;
import com.ptit.graduation.repository.product.ProductMongoRepository;
import com.ptit.graduation.service.base.impl.BaseServiceImpl;
import com.ptit.graduation.service.product.LocationService;
import com.ptit.graduation.service.product.ProductMongoService;
import com.ptit.graduation.service.product.ProductRedisService;
import com.ptit.graduation.service.product.RedisService;
import com.ptit.graduation.utils.ConvertVietnameseToNormalText;
import com.ptit.graduation.utils.NgramUtils;
import com.ptit.graduation.utils.NormalTextSearch;
import com.ptit.graduation.utils.VietnameseAccentMapper;

import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ptit.graduation.constants.GraduationProjectConstants.CommonConstants.NGRAM_COUNT;
import static com.ptit.graduation.utils.Stopword.removeStopWords;

@Slf4j
@Service
public class ProductMongoServiceImpl extends BaseServiceImpl<ProductMongo> implements ProductMongoService {
  private final ProductMongoRepository repository;
  private final MongoTemplate mongoTemplate;
  @Autowired
  private ProductRedisService productRedisService;
  @Autowired
  private LocationService locationService;
  @Autowired
  private RedisService redisService;
  @Autowired
  private ModelMapper modelMapper;

  public ProductMongoServiceImpl(ProductMongoRepository repository, MongoTemplate mongoTemplate) {
    super(repository);
    this.repository = repository;
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public void bulkInsert(List<ProductMongo> products) {
    MongoCollection<Document> collection = mongoTemplate.getCollection("products");

    List<WriteModel<Document>> operations = new ArrayList<>();
    for (ProductMongo product : products) {
      Document document = (Document) mongoTemplate.getConverter().convertToMongoType(product);
      if (document != null) {
        operations.add(new InsertOneModel<>(document));
      }
    }

    collection.bulkWrite(operations, new BulkWriteOptions().ordered(false));
    log.info("Inserted {} products using bulk operations", products.size());
  }

  @Override
  public ProductPageResponse filter(ProductFilterRequest request) {
    log.info("(filter) request: {}", request);
    String keyword = request.getKeyword();
    keyword = NormalTextSearch.normalize(keyword);
    keyword = VietnameseAccentMapper.convertToAccented(keyword);
    keyword = removeStopWords(keyword);
    keyword = fuzzySearch(keyword);

    // check if the keyword is in the cache
    List<ProductResponse> productsCache = redisService.getSearchResults(keyword+String.valueOf(request.getPage()));
    if (productsCache != null) {
      // log.info("productsCache: {}", productsCache);
      return ProductPageResponse.of(productsCache, productsCache.size());
    }

    List<String> ngrams = NgramUtils.createNGrams(keyword, NGRAM_COUNT);
    log.info("ngrams: {}", ngrams);

    List<String> locationIds = request.getLocationIds();
    if (locationIds == null || locationIds.isEmpty()) {
      locationIds = new ArrayList<>();
      List<LocationMongo> locations = locationService.list();
      for (LocationMongo location : locations) {
        locationIds.add(location.getId());
      }
      log.info("locationIds: {}", locationIds);
    }

    Criteria criteria = new Criteria().and("ngrams").all(ngrams)
        .and("review").gte(request.getReview())
        .and("location_id").in(locationIds);

    if (request.getBrandId() != null && !request.getBrandId().isEmpty()) {
      criteria = criteria.and("brand_id").is(request.getBrandId());
    }

    if (request.getCategoryId() != null && !request.getCategoryId().isEmpty()) {
      criteria = criteria.and("category_id").is(request.getCategoryId());
    }

    if (request.getPriceFrom() != null || request.getPriceTo() != null) {
      List<Criteria> priceCriteria = new ArrayList<>();
      if (request.getPriceFrom() != null && request.getPriceFrom() > 0) {
        priceCriteria.add(Criteria.where("selling_price").gte(request.getPriceFrom()));
      }
      if (request.getPriceTo() != null && request.getPriceTo() > 0) {
        priceCriteria.add(Criteria.where("selling_price").lte(request.getPriceTo()));
      }
      criteria = criteria.andOperator(priceCriteria.toArray(new Criteria[0]));
    }

    Sort sort = Sort.by(Sort.Direction.DESC, "review");

    if (request.getSort() == 1) {
      sort = Sort.by(Sort.Direction.ASC, "selling_price");
    } else if (request.getSort() == 2) {
      sort = Sort.by(Sort.Direction.DESC, "selling_price");
    }

    Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);

    Query query = new Query(criteria).with(pageable);

    List<ProductResponse> products = mongoTemplate.find(query, ProductResponse.class, "products");
    long total = mongoTemplate.count(Query.of(query).limit(-1).skip(-1), ProductResponse.class, "products");

    if (products.isEmpty()) {
      return ProductPageResponse.of(new ArrayList<>(), 0);
    }

    redisService.saveSearchResults(keyword+String.valueOf(request.getPage()), products, 10);
    return ProductPageResponse.of(products, total);
  }

  private String fuzzySearch(String keyword) {
    log.info("fuzzySearch: {}", keyword);
    ConvertVietnameseToNormalText convert = new ConvertVietnameseToNormalText();

    String[] keywordArr = keyword.split(" ");
    for (int i = 0; i < keywordArr.length; i++) {
      String text = String.join(" ", Arrays.asList(keywordArr).subList(i, keywordArr.length));
      Set<String> keySet = productRedisService.getAutosuggestByKey(text);

      int start = 0;
      while (start < text.length()) {
        String keywordTemp = text.substring(0, text.length() - start).trim();
        String keywordEnglishTemp = convert.slugify(keywordTemp);
        start++;

        for (String key : keySet) {
          String[] keyArr = key.split("&&");
          if (keyArr[0].startsWith(keywordTemp) || keyArr[1].startsWith(keywordEnglishTemp)) {
            int len = keywordTemp.split(" ").length;
            return String.join(" ", Arrays.asList(keyArr[0].split(" ")).subList(0, len));
          }
        }
      }

    }
    return keyword;
  }

  @Override
  public ProductPageResponse getAll(int skip, int limit) {
    try {
      log.info("(getAll) skip: {}, limit: {}", skip, limit);
      List<ProductMongo> products = repository.getAll(skip, limit);
      List<ProductResponse> productListResponse = products.stream()
          .map(product -> convertProductMongoToProductResponse(product))
          .collect(Collectors.toList());
      long total = products.size();
      return ProductPageResponse.of(productListResponse, total);
    } catch (Exception e) {
      log.error("Error: {}", e.getMessage());
      return ProductPageResponse.of(new ArrayList<>(), 0);
    }
  }

  @Override
  public List<ProductResponse> convertProductMongoListToProductResponses(List<ProductMongo> products) {
    return products.stream()
        .map(product -> convertProductMongoToProductResponse(product))
        .collect(Collectors.toList());
  }

  @Override
  public ProductResponse convertProductMongoToProductResponse(ProductMongo product) {
    ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
    return productResponse;
    // return ProductResponse.builder()
    //     .id(product.getId())
    //     .name(product.getName())
    //     .slug(product.getSlug())
    //     .importPrice(product.getImportPrice())
    //     .sellingPrice(product.getSellingPrice())
    //     .image(product.getImage())
    //     .isSale(product.getIsSale())
    //     .description(product.getDescription())
    //     .review(product.getReview())
    //     .location(product.getLocation())
    //     .locationId(product.getLocationId())
    //     .brandName(product.getBrandName())
    //     .brandId(product.getBrandId())
    //     .categoryName(product.getCategoryName())
    //     .categoryId(product.getCategoryId())
    //     .quantity(product.getQuantity())
    //     .soldQuantity(product.getSoldQuantity())
    //     .build();
  }





  // @Repository
  // public class ProductRepositoryCustom {
  //   private final MongoTemplate mongoTemplate;

  //   public ProductRepositoryCustom(MongoTemplate mongoTemplate) {
  //     this.mongoTemplate = mongoTemplate;
  //   }

  //   public List<Product> searchProducts(String keyword1, String keyword2) {
  //     // Match: Tìm sản phẩm có cả hai từ khóa trong ngrams
  //     MatchOperation matchStage = Aggregation.match(
  //         Criteria.where("ngrams").all(keyword1, keyword2));

  //     // Tính số lần xuất hiện từ khóa trong ngrams
  //     AddFieldsOperation keywordScore = Aggregation.addFields()
  //         .addField("keywordScore")
  //         .withValue(
  //             ConditionalOperators.sizeOfArray(
  //                 ArrayOperators.Filter.filter("ngrams")
  //                     .as("word")
  //                     .by(ComparisonOperators.In.inValue("word").in(keyword1, keyword2))))
  //         .build();

  //     // Tính điểm xuất hiện trong name
  //     AddFieldsOperation nameScore = Aggregation.addFields()
  //         .addField("nameScore")
  //         .withValue(
  //             ConditionalOperators.when(
  //                 ComparisonOperators.RegexMatch.valueOf("$name")
  //                     .regex(keyword1 + ".*" + keyword2).options("i"))
  //                 .then(5).otherwise(
  //                     ConditionalOperators.when(
  //                         ComparisonOperators.RegexMatch.valueOf("$name")
  //                             .regex(keyword1 + "|" + keyword2).options("i"))
  //                         .then(3).otherwise(0)))
  //         .build();

  //     // Tính điểm xuất hiện trong description
  //     AddFieldsOperation descriptionScore = Aggregation.addFields()
  //         .addField("descriptionScore")
  //         .withValue(
  //             ConditionalOperators.when(
  //                 ComparisonOperators.RegexMatch.valueOf("$description")
  //                     .regex(keyword1 + ".*" + keyword2).options("i"))
  //                 .then(3).otherwise(
  //                     ConditionalOperators.when(
  //                         ComparisonOperators.RegexMatch.valueOf("$description")
  //                             .regex(keyword1 + "|" + keyword2).options("i"))
  //                         .then(1).otherwise(0)))
  //         .build();

  //     // Tổng hợp điểm
  //     AddFieldsOperation totalScore = Aggregation.addFields()
  //         .addField("relevanceScore")
  //         .withValue(
  //             ArithmeticOperators.Add.add("keywordScore", "nameScore", "descriptionScore"))
  //         .build();

  //     // Sắp xếp theo relevanceScore giảm dần
  //     SortOperation sort = Aggregation.sort(Sort.by(Sort.Direction.DESC, "relevanceScore"));

  //     // Projection: Chỉ lấy các trường cần thiết
  //     ProjectionOperation project = Aggregation.project("name", "description", "keywordScore", "nameScore",
  //         "descriptionScore", "relevanceScore");

  //     // Thực hiện Aggregation Pipeline
  //     Aggregation aggregation = Aggregation.newAggregation(
  //         matchStage, keywordScore, nameScore, descriptionScore, totalScore, sort, project);

  //     return mongoTemplate.aggregate(aggregation, "products", Product.class).getMappedResults();
  //   }
  // }

}
