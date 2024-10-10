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

    Optional<MediaSocial> getBySlug(String slug);

    MediaSocial save(MediaSocial mediaSocial);

    void deleteById(String id);

    List<MediaSocial> findItemByTitle(String title);

    List<MediaSocial> filterByAuthorAndCategoryAndProducerSlug(String author_name, String category_name, String producer_name, int start, int limit);

    long countByAuthorAndCategoryAndProducerSlug(String author_name, String category_name, String producer_name);

    List<MediaSocial> searchByTitleAndFilter(String title, String author_name, String category_name, String producer_name, int start, int limit);

    long countSearchByTitleAndFilter(String title, String author_name, String category_name, String producer_name);

} 