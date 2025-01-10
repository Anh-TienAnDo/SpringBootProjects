package com.ptit.graduation.service.product;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ptit.graduation.dto.response.product.ProductResponse;
import com.ptit.graduation.entity.product.ProductMongo;

public interface ProductRedisService {
  
  ProductResponse get(String productId);

  void save(ProductResponse product);

  void saveAll(List<ProductResponse> products, int timeout, TimeUnit unit);

  List<ProductResponse> getAll(int skip, int limit);

  boolean checkProductsExist();

  void clearProdducts();

  void setKeysAutoSuggest(List<String> keys, int timeout, TimeUnit unit);

  List<Object> getKeys();

  boolean checkAutoSuggestExist();

  void clearAutoSuggest();

  void clearAllKeysInRedis();
}
