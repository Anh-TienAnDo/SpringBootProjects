package com.ptit.product_search.dto.response.product;
import com.ptit.product_search.entity.product.ProductEntity;
import com.ptit.product_search.utils.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ProductListResponse {
    private int status;
    private String message;
    private List<ProductEntity> data;
    private String timestamp;
    private int total;

    public static ProductListResponse of(int status, String message, List<ProductEntity> data, int total) {
        return of(status, message, data, DateUtils.getCurrentDateString(), total);
    }

    public static ProductListResponse ofSuccess(String message, List<ProductEntity> data, int total) {
        return of(HttpStatus.OK.value(), message, data, DateUtils.getCurrentDateString(), total);
    }

    public static ProductListResponse ofSuccess(String message, int total) {
        return of(HttpStatus.OK.value(), message, null, DateUtils.getCurrentDateString(), total);
    }

    
}
