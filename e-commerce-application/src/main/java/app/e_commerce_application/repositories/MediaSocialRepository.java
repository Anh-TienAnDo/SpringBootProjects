package app.e_commerce_application.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import app.e_commerce_application.entities.MediaSocial;

@Repository
public interface MediaSocialRepository extends MongoRepository<MediaSocial, String> {
    @Query("{'title': {$regex: ?0, $options: 'mi'}, 'is_active': true}")
    List<MediaSocial> findItemByTitleLike(String title);

    @Query(value = "{'type': ?0, 'slug': ?1, 'is_active': true}")
    Optional<MediaSocial> getByTypeAndSlug(String type, String slug);

    // get all media social
    @Aggregation(pipeline = {
        "{ $match: {'type': ?0, 'is_active': true }}",
        "{ $sort: { 'updated_at': -1 } }",
        "{ $skip: ?1 }",
        "{ $limit: ?2 }"
    })
    List<MediaSocial> getAll(String type, int start, int limit);

    @Query(value = "{'type': ?0, 'is_active': true}", count = true)
    long countAll(String type);

    // filler author + categories + producer code
    // regex + options + skip + limit -> author = "all", category = "all", producer = "all"
    @Aggregation(pipeline = {
        "{ $match: {'type': ?0, 'detail.author': {$regex: ?1, $options: 'mi'}, 'detail.categories': {$regex: ?2, $options: 'mi'}, 'detail.producer': {$regex: ?3, $options: 'mi'}, 'is_active': true }}",
        "{ $sort: { 'updated_at': -1 } }",
        "{ $skip: ?4 }",
        "{ $limit: ?5 }"
    })
    List<MediaSocial> filterByTypeAndAuthorAndCategoryAndProducerSlug(String type, String author_name, String category_name, String producer_name, int start, int limit);
    // List<MediaSocial> filterByAuthorAndCategoryAndProducerSlug(String author_slug, String category_slug, String producer_slug, int start, int limit);
    
    @Query(value="{'type': ?0, 'detail.author': {$regex: ?1, $options: 'mi'}, 'detail.categories': {$regex: ?2, $options: 'mi'}, 'detail.producer': {$regex: ?3, $options: 'mi'}, 'is_active': true }", count = true)
    long countByTypeAndAuthorAndCategoryAndProducerSlug(String type, String author_name, String category_name, String producer_name);
    
    //search + filler code
    @Aggregation(pipeline = {
        "{ $match: { 'slug': {$regex: ?0, $options: 'mi'}, 'detail.author': {$regex: ?1, $options: 'mi'}, 'detail.categories': {$regex: ?2, $options: 'mi'}, 'detail.producer': {$regex: ?3, $options: 'mi'}, 'type': {$regex: ?4, $options: 'mi'}, 'is_active': true }}",
        "{ $sort: { 'updated_at': -1 } }",
        "{ $skip: ?5 }",
        "{ $limit: ?6 }"
    })
    List<MediaSocial> searchBySlugAndFilter(String slug, String author_name, String category_name, String producer_name, String type, int start, int limit);

    @Query(value="{ 'slug': {$regex: ?0, $options: 'mi'}, 'detail.author': {$regex: ?1, $options: 'mi'}, 'detail.categories': {$regex: ?2, $options: 'mi'}, 'detail.producer': {$regex: ?3, $options: 'mi'}, 'type': {$regex: ?4, $options: 'mi'}, 'is_active': true }", count = true)
    long countSearchBySlugAndFilter(String slug, String author_name, String category_name, String producer_name, String type);

}
