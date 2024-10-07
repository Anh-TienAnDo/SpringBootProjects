package app.e_commerce_application.repositories;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import app.e_commerce_application.entities.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{price: {$gt: ?0}}")
    List<Product> findByPriceGreaterThan(double price);

    @Query("{price: {$lt: ?0}}")
    List<Product> findByPriceLessThan(double price);

    @Query("{price: {$gt: ?0, $lt: ?1}}")
    List<Product> findByPriceBetween(double min, double max);

    @Query("{name: {$regex: ?0}}")
    List<Product> findItemByNameLike(String name);
    
    public long count();
    
}
