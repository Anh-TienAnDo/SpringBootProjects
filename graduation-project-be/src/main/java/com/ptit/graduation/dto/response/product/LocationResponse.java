package com.ptit.graduation.dto.response.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.Field;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class LocationResponse implements Serializable{
  private String id;

  @JsonProperty("name")
  @Field(name = "name")
  private String name;

  @JsonProperty("is_active")
  @Field(name = "is_active")
  private boolean isActive;

}
