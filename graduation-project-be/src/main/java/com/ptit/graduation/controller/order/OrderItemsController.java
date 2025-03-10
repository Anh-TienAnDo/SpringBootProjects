package com.ptit.graduation.controller.order;

import com.ptit.graduation.dto.response.ResponseGeneral;
import com.ptit.graduation.dto.response.cart.OrderItemsResponse;
import com.ptit.graduation.entity.order.OrderItemsMySql;
import com.ptit.graduation.service.order.impl.OrderItemsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/order-items")
public class OrderItemsController {

    @Autowired
    private OrderItemsServiceImpl orderItemsService;

    @GetMapping
    public ResponseGeneral<OrderItemsResponse> getOrderItemsByOrderId(@RequestParam(value = "orderId") Long orderId) {
        return ResponseGeneral.ofSuccess(
            "SUCCESS", orderItemsService.findAllByOrderId(orderId));
    }

    // @GetMapping("/paginated")
    // public Page<OrderItemsMySql> getOrderItemsByPage(
    //         @RequestParam int page, @RequestParam int size) {
    //     return orderItemsService.getOrderItemsByPage(page, size);
    // }
}
