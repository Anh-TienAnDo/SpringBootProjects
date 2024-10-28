package com.ptit.product_search.entity.product;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ptit.product_search.entity.base.BaseEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Category")
public class CategoryEntity extends BaseEntity {

    @Field(name = "name")
    private String name;

    @Field(name = "slug")
    private String slug;

    @Field(name = "type")
    private String type;

    @Field(name = "is_active")
    private boolean isActive = true;
}
