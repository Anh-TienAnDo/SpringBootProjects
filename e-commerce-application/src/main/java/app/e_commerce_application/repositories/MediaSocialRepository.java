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

    @Query(value = "{'slug': ?0, 'is_active': true}")
    Optional<MediaSocial> getBySlug(String slug);

    // get all media social
    @Aggregation(pipeline = {
        "{ $match: {'is_active': true }}",
        "{ $sort: { 'updated_at': -1 } }",
        "{ $skip: ?0 }",
        "{ $limit: ?1 }"
    })
    List<MediaSocial> getAll(int start, int limit);

    @Query(value = "{'is_active': true}", count = true)
    long countAll();

    // filler author + categories + producer code
    // regex + options + skip + limit -> author = "all", category = "all", producer = "all"
    @Aggregation(pipeline = {
        "{ $match: { 'detail.author': {$regex: ?0, $options: 'mi'}, 'detail.categories': {$regex: ?1, $options: 'mi'}, 'detail.producer': {$regex: ?2, $options: 'mi'}, 'is_active': true }}",
        "{ $sort: { 'updated_at': -1 } }",
        "{ $skip: ?3 }",
        "{ $limit: ?4 }"
    })
    List<MediaSocial> filterByAuthorAndCategoryAndProducerSlug(String author_name, String category_name, String producer_name, int start, int limit);
    // List<MediaSocial> filterByAuthorAndCategoryAndProducerSlug(String author_slug, String category_slug, String producer_slug, int start, int limit);
    
    @Query(value="{ 'detail.author': {$regex: ?0, $options: 'mi'}, 'detail.categories': {$regex: ?1, $options: 'mi'}, 'detail.producer': {$regex: ?2, $options: 'mi'}, 'is_active': true }", count = true)
    long countByAuthorAndCategoryAndProducerSlug(String author_name, String category_name, String producer_name);
    
    //search + filler code
    @Aggregation(pipeline = {
        "{ $match: { 'title': {$regex: ?0, $options: 'mi'}, 'detail.author': {$regex: ?1, $options: 'mi'}, 'detail.categories': {$regex: ?2, $options: 'mi'}, 'detail.producer': {$regex: ?3, $options: 'mi'}, 'is_active': true }}",
        "{ $sort: { 'updated_at': -1 } }",
        "{ $skip: ?4 }",
        "{ $limit: ?5 }"
    })
    List<MediaSocial> searchByTitleAndFilter(String title, String author_name, String category_name, String producer_name, int start, int limit);

    @Query(value="{ 'title': {$regex: ?0, $options: 'mi'}, 'detail.author': {$regex: ?1, $options: 'mi'}, 'detail.categories': {$regex: ?2, $options: 'mi'}, 'detail.producer': {$regex: ?3, $options: 'mi'}, 'is_active': true }", count = true)
    long countSearchByTitleAndFilter(String title, String author_name, String category_name, String producer_name);

}
