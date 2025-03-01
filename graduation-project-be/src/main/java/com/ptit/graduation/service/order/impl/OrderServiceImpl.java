package com.ptit.graduation.service.order.impl;
import com.ptit.graduation.dto.response.cart.CartResponse;
import com.ptit.graduation.dto.response.cart.OrderResponse;
import com.ptit.graduation.entity.order.OrderItemsMySql;
import com.ptit.graduation.entity.order.OrderMySql;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.ptit.graduation.repository.order.OrderRepository;
import com.ptit.graduation.service.order.OrderItemsService;
import com.ptit.graduation.service.order.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.ptit.graduation.service.base.impl.BaseMySqlServiceImpl;
import com.ptit.graduation.service.cart.CartService;
import static com.ptit.graduation.constants.GraduationProjectConstants.CommonConstants.*;

@Slf4j
@Service
public class OrderServiceImpl extends BaseMySqlServiceImpl<OrderMySql> implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemsService orderItemsService;
    @Autowired
    private CartService cartService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemsService orderItemsService) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.orderItemsService = orderItemsService;
    }

    @Override
    public OrderResponse findAllByUserId(String userId, Timestamp time, int page) {
        int offset = (page - 1) * ORDERS_LIMIT;
        PageRequest pageRequest = PageRequest.of(offset, ORDERS_LIMIT, Sort.by(Sort.Direction.DESC, "createdAt"));
        List<OrderMySql> orders = orderRepository.findAllByUserId(userId, time, pageRequest);
        return OrderResponse.of(orders, orders.size());
    }

    @Override
    public OrderResponse findAllByUserIdAndStatus(String userId, Integer status) {
        List<OrderMySql> orders = orderRepository.findAllByUserIdAndStatus(userId, status);
        return OrderResponse.of(orders, orders.size());
    }

    @Override
    public boolean checkout(OrderMySql order) {
        List<OrderItemsMySql> orderItems = this.toOrderItems(cartService.getCartItems(order.getUserId()));
        OrderMySql savedOrder = orderRepository.save(order);
        orderItems.forEach(orderItem -> orderItem.setId(savedOrder.getId()));
        orderItemsService.batchInsert(orderItems);
        cartService.clearCart(order.getUserId());
        return true;
    }

    @Override
    public boolean cancel(Long orderId) {
        OrderMySql order = orderRepository.findById(orderId).get();
        if (order.getStatus() == 1) {
            order.setStatus(4);
            orderRepository.save(order);
        }
        return true;
    }

    public List<OrderItemsMySql> toOrderItems(CartResponse cartResponse) {
        return cartResponse.getCart().stream().map(cartItem -> {
            OrderItemsMySql orderItem = new OrderItemsMySql();
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setProductName(cartItem.getProductName());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            return orderItem;
        }).collect(Collectors.toList());
    }

    // @Override
    // public List<OrderMySql> findAllByStatus(OrderStatus status) {
    //     return orderRepository.findAllByStatus(status);
    // }

    // @Override
    // public List<OrderMySql> findAllByStatusAndDate(OrderStatus status, Date date) {
    //     return orderRepository.findAllByStatusAndDate(status, date);
    // }

    // @Override
    // public List<OrderMySql> findAllByDate(Date date) {
    //     return orderRepository.findAllByDate(date);
    // }

    // @Override
    // public List<OrderMySql> findAllByUserIdAndDate(Long userId, Date date) {
    //     return orderRepository.findAllByUserIdAndDate(userId, date);
    // }

    // @Override
    // public List<OrderMySql> findAllByUserIdAndStatusAndDate(Long userId, OrderStatus status, Date date) {
    //     return orderRepository.findAllByUserIdAndStatusAndDate(userId, status, date);
    // }

    // @Override
    // public List<OrderMySql> findAllByStatusAndDateBetween(OrderStatus status, Date startDate, Date endDate) {
    //     return orderRepository.findAllByStatusAndDateBetween(status, startDate, endDate);
    // }

    // @Override
    // public List<OrderMySql> findAllByDateBetween(Date startDate, Date endDate) {
    //     return orderRepository.findAllByDateBetween(startDate, endDate);
    // }

    // @Override
    // public List<OrderMySql> findAllByUserIdAndDateBetween(Long userId, Date startDate, Date endDate) {
    //     return orderRepository.findAllByUserIdAndDateBetween(userId, startDate, endDate);
    // }
    
}
