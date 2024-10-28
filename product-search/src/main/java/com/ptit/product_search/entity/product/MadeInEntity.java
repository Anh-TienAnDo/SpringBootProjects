package com.ptit.product_search.entity.product;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ptit.product_search.entity.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "MadeIn")
public class MadeInEntity extends BaseEntity{
    
    @Field(name = "name")
    private String name;

    @Field(name = "slug")
    private String slug;

    @Field(name = "type")
    private String type;

    @Field(name = "is_active")
    private boolean isActive = true;

    
}
