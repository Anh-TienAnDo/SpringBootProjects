package app.e_commerce_application.services;

// import org.springframework.beans.factory.annotation.Autowired;

import app.e_commerce_application.entities.MediaSocial;
// import app.e_commerce_application.entities.Detail;
// import app.e_commerce_application.repositories.MediaSocialRepository;

import java.util.List;
import java.util.Optional;

public interface MediaSocialService {

    List<MediaSocial> getAll(int start, int limit);
    
    long countAll();

    Optional<MediaSocial> getById(String id);

    Optional<MediaSocial> getByTypeAndSlug(String type, String slug);

    MediaSocial save(MediaSocial mediaSocial);

    void deleteById(String id);

    List<MediaSocial> findItemByTitle(String title);

    List<MediaSocial> filterByTypeAndAuthorAndCategoryAndProducerSlugAndTimeTotal(String type, String author_name, String category_name, String producer_name, int time_total, int start, int limit);

    long countByTypeAndAuthorAndCategoryAndProducerSlugAndTimeTotal(String type, String author_name, String category_name, String producer_name, int time_total);

    List<MediaSocial> searchBySlugAndFilter(String slug, String author_name, String category_name, String producer_name, String type, int time_total, int start, int limit);

    long countSearchBySlugAndFilter(String slug, String author_name, String category_name, String producer_name, String type, int time_total);

} 