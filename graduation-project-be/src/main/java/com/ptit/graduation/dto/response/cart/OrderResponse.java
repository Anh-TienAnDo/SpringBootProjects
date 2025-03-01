package com.ptit.graduation.dto.response.cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.ptit.graduation.entity.order.OrderMySql;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class OrderResponse {
    private List<OrderMySql> orders;
    private long total;
}
