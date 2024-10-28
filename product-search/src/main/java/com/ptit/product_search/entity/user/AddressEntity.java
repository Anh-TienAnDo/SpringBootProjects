package com.ptit.product_search.entity.user;

import org.springframework.data.mongodb.core.mapping.Field;

import com.ptit.product_search.entity.base.BaseEntity;

public class AddressEntity extends BaseEntity{

    @Field(name = "city")
    private String city;

    @Field(name = "district")
    private String district;

    @Field(name = "street")
    private String street;

    @Field(name = "address")
    private String address;

    @Field(name = "phone")
    private String phone;

    @Field(name = "is_active")
    private boolean isActive = true;

    
}
