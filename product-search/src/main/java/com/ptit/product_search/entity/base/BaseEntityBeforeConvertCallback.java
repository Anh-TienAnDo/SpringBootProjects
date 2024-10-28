package com.ptit.product_search.entity.base;

import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BaseEntityBeforeConvertCallback implements BeforeConvertCallback<BaseEntity> {
  @Override
  public BaseEntity onBeforeConvert(BaseEntity entity, String collection) {
    if (entity.getId() == null) {
      entity.setId(UUID.randomUUID().toString());
    }
    return entity;
  }
}
