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
import app.e_commerce_application.entities.Detail;

@Service
public class MediaSocialServiceImpl implements MediaSocialService {

    private ConvertVietnameseToNormalText convertVietnameseToNormalText = new ConvertVietnameseToNormalText(); 
    
    @Autowired
    private MediaSocialRepository mediaSocialRepository;

    @Autowired
    private AuthorCategoryProducerService authorCategoryProducerService;

    @Override
    public List<MediaSocial> getAll(String type, int start, int limit) {
        return mediaSocialRepository.getAll(type, start, limit); 
    }

    @Override
    public long countAll(String type) {
        return mediaSocialRepository.countAll(type);
    }

    @Override
    public Optional<MediaSocial> getById(String id) {
        return mediaSocialRepository.findById(id);
    }

    @Override
    public Optional<MediaSocial> getByTypeAndSlug(String type, String slug) {
        return mediaSocialRepository.getByTypeAndSlug(type, slug);
    }

    public void createAuthor(MediaSocial mediaSocial){
        if(mediaSocial.getDetail().getAuthor() == null){
            return;
        }
        String author_slug = convertVietnameseToNormalText.slugify(mediaSocial.getDetail().getAuthor());
        if(authorCategoryProducerService.getAuthorBySlug(author_slug).isPresent() == false){
            Author author = new Author();
            author.setName(mediaSocial.getDetail().getAuthor());
            // author.setSlug(author_slug);
            authorCategoryProducerService.saveAuthor(author);
        } 
    }

    public void createProducer(MediaSocial mediaSocial){
        if(mediaSocial.getDetail().getProducer() == null){
            return;
        }
        String producer_slug = convertVietnameseToNormalText.slugify(mediaSocial.getDetail().getProducer());
        if(authorCategoryProducerService.getProducerBySlug(producer_slug).isPresent() == false){
            Producer producer = new Producer();
            producer.setName(mediaSocial.getDetail().getProducer());
            // producer.setSlug(producer_slug);
            authorCategoryProducerService.saveProducer(producer);
        }
    }

    public void createCategory(MediaSocial mediaSocial){
        if (mediaSocial.getDetail().getCategories() == null) {
            return;
        }
        List<String> categories = mediaSocial.getDetail().getCategories();
        for (String category : categories) {
            String category_slug = convertVietnameseToNormalText.slugify(category);
            if(authorCategoryProducerService.getCategoryBySlug(category_slug).isPresent() == false){
                Category newCategory = new Category();
                newCategory.setName(category);
                // newCategory.setSlug(category_slug);
                authorCategoryProducerService.saveCategory(newCategory);
            }
        }
    }

    @Override
    public MediaSocial save(MediaSocial mediaSocial) {
        System.out.println("----------------------");
        System.out.println(" MediaSocialService.save() mediaSocial: " + mediaSocial);

        if(mediaSocial.getDetail() == null){
            mediaSocial.setDetail(new Detail());
        }
        if(mediaSocial.getDetail().getAuthor() == null){
            mediaSocial.getDetail().setAuthor("Unknown");
        }
        if(mediaSocial.getDetail().getProducer() == null){
            mediaSocial.getDetail().setProducer("Unknown");
        }
        if(mediaSocial.getDetail().getCategories() == null){
            mediaSocial.getDetail().setCategories(List.of("Unknown"));
        }
        if(mediaSocial.getDetail().getTime_total() == null){
            mediaSocial.getDetail().setTime_total(0);
        }

        if (mediaSocial.getId() != null) {
            System.out.println("MediaSocialService.save() mediaSocial.getId(): " + mediaSocial.getId());
            Optional<MediaSocial> mediaSocialOptional = this.getById(mediaSocial.getId());
            if (mediaSocialOptional.isPresent()) {
                MediaSocial existingMediaSocial = mediaSocialOptional.get();
                existingMediaSocial.setTitle(mediaSocial.getTitle());
                existingMediaSocial.setType(mediaSocial.getType());
                existingMediaSocial.setView(mediaSocial.getView());
                existingMediaSocial.setActive(mediaSocial.isActive());
                existingMediaSocial.setDetail(mediaSocial.getDetail());

                createAuthor(mediaSocial);
                createProducer(mediaSocial);
                createCategory(mediaSocial);

                return mediaSocialRepository.save(existingMediaSocial);
            }
        }

        createAuthor(mediaSocial);
        createProducer(mediaSocial);
        createCategory(mediaSocial);
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
    public List<MediaSocial> filterByTypeAndAuthorAndCategoryAndProducerSlugAndTimeTotal(String type, String author_name, String category_name, String producer_name, int time_total, int start, int limit) {
        System.out.println("MediaSocialServiceImpl.filterByTypeAndAuthorAndCategoryAndProducerSlugAndTimeTotal() type: " + type + ", author_name: " + author_name + ", category_name: " + category_name + ", producer_name: " + producer_name + ", start: " + start + ", limit: " + limit);
        return mediaSocialRepository.filterByTypeAndAuthorAndCategoryAndProducerSlugAndTimeTotal(type, author_name, category_name, producer_name, time_total, start, limit);
    }

    @Override
    public long countByTypeAndAuthorAndCategoryAndProducerSlugAndTimeTotal(String type, String author_name, String category_name, String producer_name, int time_total) {
        return mediaSocialRepository.countByTypeAndAuthorAndCategoryAndProducerSlugAndTimeTotal(type, author_name, category_name, producer_name, time_total);
    }

    @Override
    public List<MediaSocial> searchBySlugAndFilter(String query, String author_name, String category_name, String producer_name, String type, int time_total, int start, int limit) {
        String slug = convertVietnameseToNormalText.slugify(query);
        return mediaSocialRepository.searchBySlugAndFilter(slug, author_name, category_name, producer_name, type, time_total, start, limit);
    }

    @Override
    public long countSearchBySlugAndFilter(String query, String author_name, String category_name, String producer_name, String type, int time_total) {
        String slug = convertVietnameseToNormalText.slugify(query);
        return mediaSocialRepository.countSearchBySlugAndFilter(slug, author_name, category_name, producer_name, type, time_total);
    }

}
