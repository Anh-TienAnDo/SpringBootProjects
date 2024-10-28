package com.ptit.product_search.controller.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.product_search.dto.response.product.ProductListResponse;
import com.ptit.product_search.dto.response.product.ProductResponse;
import com.ptit.product_search.entity.product.ProductEntity;
import com.ptit.product_search.service.product.ProductService;

import com.ptit.product_search.entity.product.Details;
import com.ptit.product_search.entity.product.Review;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // CRUD product
    @GetMapping("")
    public ResponseEntity<ProductListResponse> list() {
        List<ProductEntity> products = productService.list();
        ProductListResponse response = ProductListResponse.ofSuccess("List products", products, products.size());
        
        return new ResponseEntity<ProductListResponse>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> create(@RequestBody ProductEntity productResponse) {
        ProductEntity product = productService.create(productResponse);
        ProductResponse response = ProductResponse.ofCreated("Product created", product);
        
        return new ResponseEntity<ProductResponse>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable String id, @RequestBody ProductEntity productResponse) {
        productResponse.setId(id);
        ProductEntity product = productService.update(productResponse);
        ProductResponse response = ProductResponse.ofSuccess("Product updated", product);
        
        return new ResponseEntity<ProductResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductResponse> get(@PathVariable String id) {
        ProductEntity product = productService.get(id);
        ProductResponse response = ProductResponse.ofSuccess("Product found", product);
        
        return new ResponseEntity<ProductResponse>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductResponse> delete(@PathVariable String id) {
        productService.delete(id);
        ProductResponse response = ProductResponse.ofSuccess("Product deleted");
        
        return new ResponseEntity<ProductResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/create-many/{number}")
    public ResponseEntity<Void> createMany(@PathVariable int number) {
        List<ProductEntity> products = generateProducts(number);
        for (ProductEntity product : products) {
            productService.create(product);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public static List<ProductEntity> generateProducts(int count) {
        List<ProductEntity> products = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            ProductEntity product = new ProductEntity();

            product.setTitle(generateProductTitle(3));

            product.setType(getRandomType());
            product.setView(random.nextInt(1000));
            product.setPriceNew(random.nextInt(10000) + 5000); // Giá mới từ 5000 - 15000
            product.setPriceOld(product.getPriceNew() + random.nextInt(5000)); // Giá cũ cao hơn giá mới

            Details details = new Details(
                getRandomCategories(),
                getRandomBrand(),
                getRandomLocations(),
                random.nextInt(100) + 1 // Quantity từ 1 đến 100
            );
            product.setDetails(details);

            Review review = new Review();
            List<Integer> points = getRandomPoints();
            review.setPointsReview(points);
            product.setReview(review);

            products.add(product);
        }
        return products;
    }

    private static String generateProductTitle(int wordCount) {
        String[] WORDS = {
            "Advanced", "Portable", "Premium", "Ultra", "Smart", "Innovative", 
            "Wireless", "Compact", "Classic", "Modern", "Durable", "Stylish", 
            "Eco-friendly", "Lightweight", "High-performance", "Ergonomic", 
            "Professional", "Multifunctional", "Rechargeable", "Eco", "Deluxe",
            "Comfort", "Waterproof", "Customizable", "Ultimate", "Efficient"
        };

        String title = "";
        for (int i = 0; i < wordCount; i++) {
            String word = WORDS[new Random().nextInt(WORDS.length)];
            title = title + word + " ";
        }
        return title.trim();
    }

    private static String getRandomType() {
        String[] types = {"Electronics", "Clothing", "Home", "Beauty", "Books"};
        return types[new Random().nextInt(types.length)];
    }

    private static List<String> getRandomCategories() {
        String[] categories = {"electronics", "clothing", "home", "beauty", "books"};
        return Arrays.stream(categories)
                     .limit(new Random().nextInt(3) + 1)  // Chọn 1-3 categories ngẫu nhiên
                     .collect(Collectors.toList());
    }

    private static String getRandomBrand() {
        String[] brands = {"brandA", "brandB", "brandC", "brandD"};
        return brands[new Random().nextInt(brands.length)];
    }

    private static List<String> getRandomLocations() {
        String[] locations = {"Ha Noi", "Sai Gon", "Da Nang"};
        return Arrays.asList(locations).subList(0, new Random().nextInt(locations.length) + 1);
    }

    private static List<Integer> getRandomPoints() {
        return new Random().ints(5, 1, 6)  // 5 điểm từ 1 đến 5
                           .boxed()
                           .collect(Collectors.toList());
    }
    
}
