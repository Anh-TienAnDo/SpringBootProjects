package com.ptit.graduation.service.product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ptit.graduation.dto.response.product.ProductResponse;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
  private final StringRedisTemplate redisTemplate;
  private final ObjectMapper objectMapper;

  public RedisService(StringRedisTemplate redisTemplate, ObjectMapper objectMapper) {
    this.redisTemplate = redisTemplate;
    this.objectMapper = objectMapper;
  }

  public List<ProductResponse> getSearchResults(String keyword) {
    String cachedData = redisTemplate.opsForValue().get(keyword);
    if (cachedData != null) {
      try {
        return objectMapper.readValue(cachedData, new TypeReference<List<ProductResponse>>() {
        });
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public void saveSearchResults(String keyword, List<ProductResponse> results, long duration) {
    try {
      String data = objectMapper.writeValueAsString(results);
      redisTemplate.opsForValue().set(keyword, data, duration, TimeUnit.MINUTES);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void clearCache(String keyword) {
    redisTemplate.delete(keyword);
  }
}
