package app.e_commerce_application.repositories;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import app.e_commerce_application.entities.MediaSocial;

public interface MediaSocialRepository extends MongoRepository<MediaSocial, String> {
    // db["media_socials"].find({'detail.category': {$regex: 'Video'}});
    // db["media_socials"].find({'detail.category': {$regex: ''}}); tìm tất cả các tài liệu có trường category
    @Query("{'title': {$regex: ?0}}")
    List<MediaSocial> findItemByNameLike(String title);

    @Aggregation(pipeline = {
        "{ $match: { 'detail.author': {$regex: ?0, $options: 'mi'}, 'detail.categories': {$regex: ?1, $options: 'mi'}, 'detail.producer': {$regex: ?2, $options: 'mi'}, 'is_active': true }}",
        "{ $sort: { 'updatedAt': -1 } }",
        "{ $skip: ?3 }",
        "{ $limit: ?4 }"
    })
    List<MediaSocial> findByAuthorAndCategoryAndProducerSlug(String author, String category, String producer, int start, int limit);
    
    public long count();
    
}
