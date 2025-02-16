package com.ptit.graduation.service.product;

import com.ptit.graduation.dto.request.product.ProductFilterRequest;
import com.ptit.graduation.dto.response.product.ProductPageResponse;
import com.ptit.graduation.dto.response.product.ProductResponse;
import com.ptit.graduation.entity.product.ProductMongo;
import com.ptit.graduation.service.base.BaseService;

import java.util.List;


public interface ProductMongoService extends BaseService<ProductMongo> {
  void bulkInsert(List<ProductMongo> products);

  ProductPageResponse filter(ProductFilterRequest request);

  ProductPageResponse getAll(int skip, int limit);

  ProductResponse convertProductMongoToProductResponse(ProductMongo product);

}
