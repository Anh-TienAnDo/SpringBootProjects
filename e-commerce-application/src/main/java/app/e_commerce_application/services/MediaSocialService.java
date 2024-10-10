package app.e_commerce_application.services;

// import org.springframework.beans.factory.annotation.Autowired;

import app.e_commerce_application.entities.MediaSocial;
// import app.e_commerce_application.entities.Detail;
// import app.e_commerce_application.repositories.MediaSocialRepository;

import java.util.List;
import java.util.Optional;

public interface MediaSocialService {

    List<MediaSocial> getAll(String type, int start, int limit);
    
    long countAll(String type);

    Optional<MediaSocial> getById(String id);

    Optional<MediaSocial> getByTypeAndSlug(String type, String slug);

    MediaSocial save(MediaSocial mediaSocial);

    void deleteById(String id);

    List<MediaSocial> findItemByTitle(String title);

    List<MediaSocial> filterByTypeAndAuthorAndCategoryAndProducerSlug(String type, String author_name, String category_name, String producer_name, int start, int limit);

    long countByTypeAndAuthorAndCategoryAndProducerSlug(String type, String author_name, String category_name, String producer_name);

    List<MediaSocial> searchByTitleAndFilter(String title, String author_name, String category_name, String producer_name, String type, int start, int limit);

    long countSearchByTitleAndFilter(String title, String author_name, String category_name, String producer_name, String type);

} 