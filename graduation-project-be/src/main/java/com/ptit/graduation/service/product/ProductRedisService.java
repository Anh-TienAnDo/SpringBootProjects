package com.ptit.graduation.service.product;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.ptit.graduation.dto.response.product.ProductResponse;

public interface ProductRedisService {
  
  ProductResponse get(String productId);

  void save(ProductResponse product);

  void saveAll(List<ProductResponse> products, int timeout, TimeUnit unit);

  List<ProductResponse> getAll(int skip, int limit);

  boolean checkProductsExist();

  void clearProdducts();

  void setKeysAutoSuggest(List<String> keys, int timeout, TimeUnit unit);

  Set<String> getAutosuggestByKey(String text);

  boolean checkAutoSuggestExist();

  void clearAutoSuggest();

  void clearAllKeysInRedis();
}
