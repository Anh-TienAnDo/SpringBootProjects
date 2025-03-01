package com.ptit.graduation.entity.order;
import lombok.*;
import jakarta.persistence.*;

import jakarta.persistence.Column;
import com.ptit.graduation.entity.base.BaseMysqlEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
// create, get all by orderid
public class OrderItemsMySql extends BaseMysqlEntity {
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Long price;

}
