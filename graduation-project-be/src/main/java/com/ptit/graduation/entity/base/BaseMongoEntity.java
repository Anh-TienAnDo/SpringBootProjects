package com.ptit.graduation.entity.base;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
// Getters và Setters cho tất cả các fields.
// toString(), equals() và hashCode()
@NoArgsConstructor // tự động tạo constructor không có tham số.
@AllArgsConstructor // tự động tạo constructor có đầy đủ tham số cho tất cả fields
public abstract class BaseMongoEntity {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
  @Id
  private String id;

  @CreatedDate
  @Field(name = "created_at")
  private Long createdAt; // Một Long (8 bytes) nhỏ hơn một Date hoặc LocalDateTime

  @LastModifiedDate
  @Field(name = "updated_at")
  private Long updatedAt;

  @CreatedBy
  @Field(name = "created_by")
  private String createdBy;

  @LastModifiedBy
  @Field(name = "updated_by")
  private String updatedBy;

}

