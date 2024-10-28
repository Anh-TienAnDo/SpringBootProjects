package com.ptit.product_search.repository.product;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ptit.product_search.entity.product.ProductEntity;
import com.ptit.product_search.repository.base.BaseRepository;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity> {

    // @Query(value="{'type': {$regex: ?0, $options: 'mi'}, 'detail.author': {$regex: ?1, $options: 'mi'}, 'detail.categories': {$regex: ?2, $options: 'mi'}, 'detail.producer': {$regex: ?3, $options: 'mi'}, 'detail.time_total': {$gte: ?4}, 'is_active': true }", count = true)
    
    // @Aggregation(pipeline = {
    //     "{ $match: {'type': {$regex: ?0, $options: 'mi'}, 'detail.author': {$regex: ?1, $options: 'mi'}, 'detail.categories': {$regex: ?2, $options: 'mi'}, 'detail.producer': {$regex: ?3, $options: 'mi'}, 'detail.time_total': {$gte: ?4}, 'is_active': true }}",
    //     "{ $sort: { 'updated_at': -1 } }",
    //     "{ $skip: ?5 }",
    //     "{ $limit: ?6 }"
    // })
} 
