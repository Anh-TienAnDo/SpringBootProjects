package com.ptit.graduation.service.order;
import com.ptit.graduation.dto.response.cart.OrderResponse;
import com.ptit.graduation.entity.order.OrderMySql;
import com.ptit.graduation.service.base.BaseMySqlService;

import java.sql.Timestamp;

public interface OrderService extends BaseMySqlService<OrderMySql> {
    public OrderResponse findAllByUserId(String userId, Timestamp time, int page);

    public OrderResponse findAllByUserIdAndStatus(String userId, Integer status);

    public boolean checkout(OrderMySql order);

    public boolean cancel(Long orderId);
}
