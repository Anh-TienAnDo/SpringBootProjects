package com.ptit.product_search.repository.product;

import java.util.Optional;

import org.springframework.data.mongodb.repository.Query;

import com.ptit.product_search.entity.product.LocationEntity;
import com.ptit.product_search.repository.base.BaseRepository;

public interface LocationRepository extends BaseRepository<LocationEntity> {

    @Query(value = "{'slug': ?0, 'is_active': true}")
    LocationEntity findBySlug(String slug);

    @Query(value = "{'slug': ?0, 'type': ?1, 'is_active': true}")
    LocationEntity findBySlugAndType(String slug, String type);
    
}
