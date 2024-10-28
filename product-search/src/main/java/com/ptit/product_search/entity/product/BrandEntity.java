package com.ptit.product_search.entity.product;

import org.springframework.data.mongodb.core.mapping.Field;

import com.ptit.product_search.entity.base.BaseEntity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Brand")
public class BrandEntity extends BaseEntity{

    @Field(name = "name")
    private String name;

    @Field(name = "slug")
    private String slug;

    @Field(name = "type")
    private String type;

    @Field(name = "email")
    private String email;

    @Field(name = "is_active")
    private boolean isActive = true;
}
