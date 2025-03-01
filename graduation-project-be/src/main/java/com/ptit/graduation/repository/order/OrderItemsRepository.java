package com.ptit.graduation.repository.order;
import java.util.List;

import com.ptit.graduation.entity.order.OrderItemsMySql;
import com.ptit.graduation.repository.base.BaseMySqlRepository;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.jpa.repository.Query;

public interface OrderItemsRepository extends BaseMySqlRepository<OrderItemsMySql> {
    
    List<OrderItemsMySql> findAllByOrderId(Long orderId);

    List<OrderItemsMySql> findAllByProductId(String productId);

    // @Query("SELECT o FROM OrderItemsMySql o ORDER BY o.orderDate DESC")
    // Page<OrderItemsMySql> findAllOrderByOrderDateDesc(Pageable pageable);
    
}
