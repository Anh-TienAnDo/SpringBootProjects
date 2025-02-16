package com.ptit.graduation.dto.response.product;

import com.ptit.graduation.entity.product.LocationMongo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class LocationListResponse {
  private List<LocationMongo> locations;
  private long total;
}
