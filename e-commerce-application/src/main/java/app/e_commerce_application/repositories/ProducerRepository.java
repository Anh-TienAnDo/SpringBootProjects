package app.e_commerce_application.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import app.e_commerce_application.entities.Producer;

@Repository
public interface ProducerRepository extends MongoRepository<Producer, String> {

    @Aggregation(pipeline = {
        "{ $match: {'is_active': true }}",
        "{ $sort: { 'updated_at': -1 } }",
    })
    List<Producer> getAll();

    @Query(value="{'name': {$regex: ?0, $options: 'mi'}, 'is_active': true}")
    List<Producer> findItemByNameLike(String name);

    @Query(value = "{'slug': ?0, 'is_active': true}")
    Optional<Producer> getBySlug(String slug);
    
}
