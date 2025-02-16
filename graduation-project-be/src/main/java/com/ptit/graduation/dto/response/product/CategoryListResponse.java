package com.ptit.graduation.dto.response.product;

import com.ptit.graduation.entity.product.CategoryMongo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class CategoryListResponse {
  private List<CategoryMongo> data;
  private long total;

}
