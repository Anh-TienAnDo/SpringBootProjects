package com.ptit.graduation.dto.response.cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.ptit.graduation.entity.order.OrderItemsMySql;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class OrderItemsResponse {
    private List<OrderItemsMySql> orderItems;
    private long total;
    
}
