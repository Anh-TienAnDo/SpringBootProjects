package com.ptit.product_search.entity.order;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import com.ptit.product_search.entity.base.BaseEntity;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity extends BaseEntity{

    @Field(name = "user_id")
    private String userId;

    @Field(name = "code")
    private String code;

    @Field(name = "total_price")
    private Integer totalPrice;

    @Field(name = "status")
    private String status;

    @Field(name = "order_items")
    private List<OrderItem> orderItems;
    
}
