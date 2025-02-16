package com.ptit.graduation.dto.response.product;

import com.ptit.graduation.entity.product.BrandMongo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class BrandListResponse {
  private List<BrandMongo> data;
  private long total;

}
