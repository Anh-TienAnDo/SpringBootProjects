package com.ptit.graduation.service.product.impl;

import com.ptit.graduation.dto.response.product.ProductResponse;
import com.ptit.graduation.service.product.ProductRedisService;
import com.ptit.graduation.utils.ConvertVietnameseToNormalText;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.ptit.graduation.constants.GraduationProjectConstants.CommonConstants.PRODUCT_HASH_KEY;
import static com.ptit.graduation.constants.GraduationProjectConstants.CommonConstants.AUTO_SUGGEST_KEY;
import static com.ptit.graduation.constants.GraduationProjectConstants.CommonConstants.TIME_DAYS;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductRedisServiceImpl implements ProductRedisService {
  // private static final String PRODUCT_HASH_KEY = "Products";
  // private static final String AUTO_SUGGEST_KEY = "AutoSuggestKey";

  private final RedisTemplate<String, Object> redisTemplate;

  private HashOperations<String, String, Object> hashOperations;

  @PostConstruct
  private void init() {
    hashOperations = redisTemplate.opsForHash();
    if (!checkProductsExist()) {
      redisTemplate.expire(PRODUCT_HASH_KEY, TIME_DAYS, TimeUnit.DAYS);
    }
  }

  @Override
  public void saveAll(List<ProductResponse> products, int timeout, TimeUnit unit) {
    for (ProductResponse product : products) {
      save(product);
    }
    // redisTemplate.expire(PRODUCT_HASH_KEY, timeout, unit);
  }

  @Override
  public void save(ProductResponse product) {
    hashOperations.put(PRODUCT_HASH_KEY, product.getId(), product);
  }

  @Override
  public List<ProductResponse> getAll(int skip, int limit) {
    return hashOperations.entries(PRODUCT_HASH_KEY).values().stream()
        .skip(skip)
        .limit(limit)
        .map(product -> (ProductResponse) product)
        .collect(Collectors.toList());
  }

  @Override
  public ProductResponse get(String productId) {
    return (ProductResponse) hashOperations.get(PRODUCT_HASH_KEY, productId);
  }

  @Override
  public boolean checkProductsExist() {
    return redisTemplate.hasKey(PRODUCT_HASH_KEY);
  }

  @Override
  public void clearProdducts() {
    redisTemplate.delete(PRODUCT_HASH_KEY);
  }

  @Override
  public void setKeysAutoSuggest(List<String> keys, int timeout, TimeUnit unit) {
    // [{ "m": ["máy tính&&may-tinh", "mè&&me"] }, { "b": ["bánh&&banh"] }]
    List<Map<String, Set<String>>> mapkeys = new ArrayList<>();
    ConvertVietnameseToNormalText convert = new ConvertVietnameseToNormalText();
    for (int i = 0; i < keys.size(); i++) {
      String value = keys.get(i).trim().toLowerCase();
      String valueSlug = convert.slugify(value);
      String prefix = valueSlug.substring(0, 1);
      
      boolean isExist = false;
      for (int j = 0; j < mapkeys.size(); j++) {
        if (mapkeys.get(j).containsKey(prefix)) {
          mapkeys.get(j).get(prefix).add(value + "&&" + valueSlug);
          isExist = true;
          break;
        }
      }
      if (!isExist) {
        Map<String, Set<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        set.add(value + "&&" + valueSlug);
        map.put(prefix, set);
        mapkeys.add(map);
      }
    }
    for (Map<String, Set<String>> map : mapkeys) {
      for (String key : map.keySet()) {
        redisTemplate.opsForSet().add(String.format(AUTO_SUGGEST_KEY + ":%s", key), map.get(key).toArray());
        redisTemplate.expire(String.format(AUTO_SUGGEST_KEY + ":%s", key), timeout, unit);
      }
    }
    // redisTemplate.opsForList().rightPushAll(AUTO_SUGGEST_KEY, mapkeys.toArray());
    // redisTemplate.expire(AUTO_SUGGEST_KEY, timeout, unit);
  }

  @Override
  public Set<String> getAutosuggestByKey(String text) {
    ConvertVietnameseToNormalText convert = new ConvertVietnameseToNormalText();
    String prefix = convert.slugify(text).substring(0, 1);
    Set<String> result = new HashSet<>();
    result.addAll((Set<String>) (Set<?>) redisTemplate.opsForSet().members(String.format(AUTO_SUGGEST_KEY + ":%s", prefix)));
    return result;
  }


  @Override
  public boolean checkAutoSuggestExist() {
    return redisTemplate.hasKey(AUTO_SUGGEST_KEY);
  }

  @Override
  public void clearAutoSuggest() {
    // String s = "abcdefghijklmnopqrstuvwxyz";
    // for (int i = 0; i < s.length(); i++) {
    //   redisTemplate.delete(String.format(AUTO_SUGGEST_KEY + ":%s", s.charAt(i)));
    // }
    Set<String> keys = redisTemplate.keys(AUTO_SUGGEST_KEY + ":*");
    if (keys != null) {
      redisTemplate.delete(keys);
    }
  }

  @Override
  public void clearAllKeysInRedis() {
    Set<String> keys = redisTemplate.keys("*");
    if (keys != null) {
      redisTemplate.delete(keys);
    }
  }

}
