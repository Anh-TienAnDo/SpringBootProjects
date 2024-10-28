package com.ptit.product_search.repository.product;

import java.util.Optional;

import org.springframework.data.mongodb.repository.Query;

import com.ptit.product_search.entity.product.BrandEntity;
import com.ptit.product_search.repository.base.BaseRepository;

public interface BrandRepository extends BaseRepository<BrandEntity> {

    @Query(value = "{'slug': ?0, 'is_active': true}")
    BrandEntity findBySlug(String slug);

    @Query(value = "{'slug': ?0, 'type': ?1, 'is_active': true}")
    BrandEntity findBySlugAndType(String slug, String type);
    
}
