package com.ptit.graduation.dto.response.product;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ProductPageResponse {
    private List<ProductResponse> products;
    private long total;
}
