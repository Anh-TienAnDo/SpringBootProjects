package com.ptit.product_search.repository.product;

import java.util.Optional;

import org.springframework.data.mongodb.repository.Query;

import com.ptit.product_search.entity.product.CategoryEntity;
import com.ptit.product_search.repository.base.BaseRepository;

public interface CategoryRepository extends BaseRepository<CategoryEntity> {
    
    @Query(value = "{'slug': ?0, 'is_active': true}")
    CategoryEntity findBySlug(String slug);

    @Query(value = "{'slug': ?0, 'type': ?1, 'is_active': true}")
    CategoryEntity findBySlugAndType(String slug, String type);

}
