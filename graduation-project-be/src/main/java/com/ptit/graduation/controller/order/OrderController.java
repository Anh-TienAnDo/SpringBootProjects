package com.ptit.graduation.controller.order;

import java.sql.Timestamp;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.graduation.dto.response.ResponseGeneral;
import com.ptit.graduation.dto.response.cart.OrderItemsResponse;
import com.ptit.graduation.dto.response.cart.OrderResponse;
import com.ptit.graduation.entity.order.OrderMySql;
import com.ptit.graduation.service.order.OrderItemsService;
import com.ptit.graduation.service.order.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemsService orderItemsService;

    public OrderController(OrderService orderService, OrderItemsService orderItemsService) {
        this.orderService = orderService;
        this.orderItemsService = orderItemsService;
    }

    @GetMapping("")
    public ResponseGeneral<OrderResponse> getAllOrderByUserId(
        @RequestParam() String time,
        @RequestParam(defaultValue = "1") int page
    ) {
        Timestamp timestamp = Timestamp.valueOf(time);
        return ResponseGeneral.ofSuccess(
            "SUCCESS", orderService.findAllByUserId("anh", timestamp, page));
    }

    @GetMapping("/get/{id}")
    public ResponseGeneral<OrderItemsResponse> getOrderById(@RequestParam() Long id) {
        return ResponseGeneral.ofSuccess(
            "SUCCESS", orderItemsService.findAllByOrderId(id));
    }

    @PostMapping("")
    public ResponseGeneral<String> createOrder(
        @RequestBody OrderMySql order) {
        orderService.checkout(order);
        return ResponseGeneral.ofCreated("SUCCESS", "Order created successfully");
    }

    @DeleteMapping("/order/{id}")
    public ResponseGeneral<String> cancelOrder(@RequestParam() Long id) {
        orderService.cancel(id);
        return ResponseGeneral.ofSuccess("SUCCESS", "Order canceled successfully");
    }

}
