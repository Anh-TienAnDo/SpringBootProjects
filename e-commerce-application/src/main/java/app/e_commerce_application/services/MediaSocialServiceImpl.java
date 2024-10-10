package app.e_commerce_application.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.e_commerce_application.entities.MediaSocial;
import app.e_commerce_application.entities.Producer;
import app.e_commerce_application.helper.ConvertVietnameseToNormalText;
import app.e_commerce_application.repositories.MediaSocialRepository;
import app.e_commerce_application.entities.Author;
import app.e_commerce_application.entities.Category;
// import java.util.ArrayList;
// import app.e_commerce_application.entities.Detail;

@Service
public class MediaSocialServiceImpl implements MediaSocialService {

    private ConvertVietnameseToNormalText convertVietnameseToNormalText = new ConvertVietnameseToNormalText(); 
    
    @Autowired
    private MediaSocialRepository mediaSocialRepository;

    @Autowired
    private AuthorCategoryProducerService authorCategoryProducerService;

    @Override
    public List<MediaSocial> getAll(int start, int limit) {
        return mediaSocialRepository.getAll(start, limit); 
    }

    @Override
    public long countAll() {
        return mediaSocialRepository.countAll();
    }

    @Override
    public Optional<MediaSocial> getById(String id) {
        return mediaSocialRepository.findById(id);
    }

    @Override
    public Optional<MediaSocial> getBySlug(String slug) {
        return mediaSocialRepository.getBySlug(slug);
    }

    @Override
    public MediaSocial save(MediaSocial mediaSocial) {
        System.out.println("----------------------");
        System.out.println(" MediaSocialService.save() mediaSocial: " + mediaSocial);
        if (mediaSocial.getId() != null) {
            Optional<MediaSocial> mediaSocialOptional = this.getById(mediaSocial.getId());
            if (mediaSocialOptional.isPresent()) {
                MediaSocial existingMediaSocial = mediaSocialOptional.get();
                existingMediaSocial.setTitle(mediaSocial.getTitle());
                existingMediaSocial.setType(mediaSocial.getType());
                existingMediaSocial.setView(mediaSocial.getView());
                existingMediaSocial.setActive(mediaSocial.isActive());
                existingMediaSocial.setDetail(mediaSocial.getDetail());

                String author_slug = convertVietnameseToNormalText.slugify(mediaSocial.getDetail().getAuthor());
                String producer_slug = convertVietnameseToNormalText.slugify(mediaSocial.getDetail().getProducer());
                if(!authorCategoryProducerService.getAuthorBySlug(author_slug).isPresent()){
                    Author author = new Author();
                    author.setName(mediaSocial.getDetail().getAuthor());
                    // author.setSlug(author_slug);
                    authorCategoryProducerService.saveAuthor(author);
                } 

                if(!authorCategoryProducerService.getProducerBySlug(producer_slug).isPresent()){
                    Producer producer = new Producer();
                    producer.setName(mediaSocial.getDetail().getProducer());
                    // producer.setSlug(producer_slug);
                    authorCategoryProducerService.saveProducer(producer);
                }

                List<String> categories = mediaSocial.getDetail().getCategories();
                for (String category : categories) {
                    String category_slug = convertVietnameseToNormalText.slugify(category);
                    if(!authorCategoryProducerService.getCategoryBySlug(category_slug).isPresent()){
                        Category newCategory = new Category();
                        newCategory.setName(category);
                        // newCategory.setSlug(category_slug);
                        authorCategoryProducerService.saveCategory(newCategory);
                    }
                }
                return mediaSocialRepository.save(existingMediaSocial);
            }
        }
        return mediaSocialRepository.save(mediaSocial);
    }

    @Override
    public void deleteById(String id) {
        mediaSocialRepository.deleteById(id);
    }

    @Override
    public List<MediaSocial> findItemByTitle(String title) {
        return mediaSocialRepository.findItemByTitleLike(title);
    }

    @Override
    public List<MediaSocial> filterByAuthorAndCategoryAndProducerSlug(String author_name, String category_name, String producer_name, int start, int limit) {
        // String author_slug = convertVietnameseToNormalText.slugify(author_title);
        // String category_slug = convertVietnameseToNormalText.slugify(category_title);
        // String producer_slug = convertVietnameseToNormalText.slugify(producer_title);
        return mediaSocialRepository.filterByAuthorAndCategoryAndProducerSlug(author_name, category_name, producer_name, start, limit);
    }

    @Override
    public long countByAuthorAndCategoryAndProducerSlug(String author_name, String category_name, String producer_name) {
        // String author_slug = convertVietnameseToNormalText.slugify(author_title);
        // String category_slug = convertVietnameseToNormalText.slugify(category_title);
        // String producer_slug = convertVietnameseToNormalText.slugify(producer_title);
        return mediaSocialRepository.countByAuthorAndCategoryAndProducerSlug(author_name, category_name, producer_name);
    }

    @Override
    public List<MediaSocial> searchByTitleAndFilter(String title, String author_name, String category_name, String producer_name, int start, int limit) {
        // String author_slug = convertVietnameseToNormalText.slugify(author_title);
        // String category_slug = convertVietnameseToNormalText.slugify(category_title);
        // String producer_slug = convertVietnameseToNormalText.slugify(producer_title);
        return mediaSocialRepository.searchByTitleAndFilter(title, author_name, category_name, producer_name, start, limit);
    }

    @Override
    public long countSearchByTitleAndFilter(String title, String author_name, String category_name, String producer_name) {
        // String author_slug = convertVietnameseToNormalText.slugify(author_title);
        // String category_slug = convertVietnameseToNormalText.slugify(category_title);
        // String producer_slug = convertVietnameseToNormalText.slugify(producer_title);
        return mediaSocialRepository.countSearchByTitleAndFilter(title, author_name, category_name, producer_name);
    }

}
