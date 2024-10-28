package com.ptit.product_search.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = {"com.ptit.product_search.repository"})
public class MongoServiceConfiguration {

}

