package com.ptit.graduation.entity.order;
import lombok.*;
import jakarta.persistence.*;

import jakarta.persistence.Table;

import com.ptit.graduation.entity.base.BaseMysqlEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
// create, cancel, get all by userid
public class OrderMySql extends BaseMysqlEntity {
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "status")
    private Integer status; // status: 1: processing, 2: shipping, 3: success, 4: cancel

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "note")
    private String note;
    
}
