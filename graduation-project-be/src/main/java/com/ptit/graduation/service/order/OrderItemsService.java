package com.ptit.graduation.service.order;
import com.ptit.graduation.dto.response.cart.OrderItemsResponse;
import com.ptit.graduation.entity.order.OrderItemsMySql;
import com.ptit.graduation.service.base.BaseMySqlService;

import java.util.List;

public interface OrderItemsService extends BaseMySqlService<OrderItemsMySql> {
    public OrderItemsResponse findAllByOrderId(Long orderId);

    public void batchInsert(List<OrderItemsMySql> orderItems);
    
}
