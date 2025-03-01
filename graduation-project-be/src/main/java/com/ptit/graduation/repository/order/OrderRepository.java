package com.ptit.graduation.repository.order;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ptit.graduation.entity.order.OrderMySql;
import com.ptit.graduation.repository.base.BaseMySqlRepository;

@Repository
public interface OrderRepository extends BaseMySqlRepository<OrderMySql> {

    @Query("""
            SELECT o FROM OrderMySql o
            WHERE o.userId = ?1 AND o.createdAt < ?2
            """)
    List<OrderMySql> findAllByUserId(String userId, Timestamp time, Pageable pageable);

    List<OrderMySql> findAllByUserIdAndStatus(String userId, Integer status);

}
