package com.ptit.product_search.entity.order;

import org.springframework.data.mongodb.core.mapping.Field;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Field(name = "product_title")
    private String productTitle;

    @Field(name = "quantity")
    private Integer quantity;

    @Field(name = "price")
    private Integer price;


}
