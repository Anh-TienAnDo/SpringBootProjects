package com.ptit.product_search.dto.response.product;
import com.ptit.product_search.entity.product.ProductEntity;
import com.ptit.product_search.utils.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ProductResponse {
    private int status;
    private String message;
    private ProductEntity data;
    private String timestamp;

    public static ProductResponse of(int status, String message, ProductEntity data) {
        return of(status, message, data, DateUtils.getCurrentDateString());
    }

    public static ProductResponse ofSuccess(String message, ProductEntity data) {
        return of(HttpStatus.OK.value(), message, data, DateUtils.getCurrentDateString());
    }

    public static ProductResponse ofSuccess(String message) {
        return of(HttpStatus.OK.value(), message, null, DateUtils.getCurrentDateString());
    }

    public static ProductResponse ofCreated(String message, ProductEntity data) {
        return of(HttpStatus.CREATED.value(), message, data, DateUtils.getCurrentDateString());
    }
}
