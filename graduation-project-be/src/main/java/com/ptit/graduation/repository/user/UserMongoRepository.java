package com.ptit.graduation.repository.user;

import org.springframework.data.mongodb.repository.Aggregation;

import com.ptit.graduation.entity.user.UserMongo;
import com.ptit.graduation.repository.base.BaseMongoRepository;

public interface UserMongoRepository extends BaseMongoRepository<UserMongo> {
    
    @Aggregation(pipeline = {
        "{ $match: { 'username': ?0, 'password': ?1, 'is_active': true }}"
    })
    UserMongo login(String username, String password);

    @Aggregation(pipeline = {
        "{ $match: { 'username': ?0, 'is_active': true }}"
    })
    UserMongo findByUsername(String username);

    @Aggregation(pipeline = {
        "{ $match: { 'email': ?0, 'is_active': true }}"
    })
    UserMongo findByEmail(String email);

    
}
