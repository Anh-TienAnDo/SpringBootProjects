package com.ptit.graduation.service.order.impl;
import com.ptit.graduation.dto.response.cart.OrderItemsResponse;
import com.ptit.graduation.entity.order.OrderItemsMySql;
import java.util.List;
import com.ptit.graduation.repository.order.OrderItemsRepository;
import com.ptit.graduation.service.order.OrderItemsService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.ptit.graduation.service.base.impl.BaseMySqlServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Slf4j
@Service
public class OrderItemsServiceImpl extends BaseMySqlServiceImpl<OrderItemsMySql> implements OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public OrderItemsServiceImpl(OrderItemsRepository orderItemsRepository) {
        super(orderItemsRepository);
        this.orderItemsRepository = orderItemsRepository;
    }

    @Override
    public OrderItemsResponse findAllByOrderId(Long orderId) {
        List<OrderItemsMySql> items = orderItemsRepository.findAllByOrderId(orderId);
        return OrderItemsResponse.of(items, items.size());
        // return this.orderItemsRepository.findAllByOrderId(orderId);
    }

    @Override
    @Transactional
    public void batchInsert(List<OrderItemsMySql> orderItems) {
        int batchSize = 1000;
        for (int i = 0; i < orderItems.size(); i++) {
            entityManager.persist(orderItems.get(i));
            if (i % batchSize == 0 && i > 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }

    // public Page<OrderItemsMySql> getOrderItemsByPage(int page, int size) {
    //     Pageable pageable = PageRequest.of(page, size);
    //     return orderItemsRepository.findAllOrderByOrderDateDesc(pageable);
    // }
    
    // @Override
    // public List<OrderItems> findAllByProductId(Long productId) {
    //     return orderItemsRepository.findAllByProductId(productId);
    // }

    // @Override
    // public List<OrderItems> findAllByOrderIdAndProductId(Long orderId, Long productId) {
    //     return orderItemsRepository.findAllByOrderIdAndProductId(orderId, productId);
    // }

    // @Override
    // public List<OrderItems> findAllByOrderIdAndStatus(Long orderId, String status) {
    //     return orderItemsRepository.findAllByOrderIdAndStatus(orderId, status);
    // }

    // @Override
    // public List<OrderItems> findAllByProductIdAndStatus(Long productId, String status) {
    //     return orderItemsRepository.findAllByProductIdAndStatus(productId, status);
    // }

    // @Override
    // public List<OrderItems> findAllByOrderIdAndProductIdAndStatus(Long orderId, Long productId, String status) {
    //     return orderItemsRepository.findAllByOrderIdAndProductIdAndStatus(orderId, productId, status);
    // }

    // @Override
    // public List<OrderItems> findAllByOrderIdAndProductIdAndStatusAndQuantity(Long orderId, Long productId, String status, int quantity) {
    //     return orderItemsRepository.findAllByOrderIdAndProductIdAndStatusAndQuantity(orderId, productId, status, quantity);
    // }

    // @Override
    // public List<OrderItems> findAllByOrderIdAndProductIdAndStatusAndPrice(Long orderId, Long productId, String status, double price) {
    //     return orderItemsRepository.findAllByOrderIdAndProductIdAndStatusAndPrice(orderId, productId, status, price);
    // }

    // @Override
    // public List<OrderItems> findAllByOrderIdAndProductIdAndStatusAndQuantityAndPrice(Long orderId, Long productId, String status, int quantity, double price) {
    //     return orderItemsRepository.findAllByOrderIdAndProductIdAndStatusAndQuantityAndPrice(orderId, productId, status, quantity, price);
    // }
    
}
