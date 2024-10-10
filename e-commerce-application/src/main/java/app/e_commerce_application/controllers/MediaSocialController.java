package app.e_commerce_application.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.e_commerce_application.entities.MediaSocial;
import app.e_commerce_application.payloads.MediaSocialResponse;
import app.e_commerce_application.payloads.MediaSocialsResponse;
import app.e_commerce_application.config.Data;
// import app.e_commerce_application.entities.Detail;
import app.e_commerce_application.services.MediaSocialService;

// import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/media-socials")
public class MediaSocialController {

    @Autowired
    private MediaSocialService mediaSocialService;

    private Data dataConfig = new Data();
    private Integer start = dataConfig.getStart();
    private Integer limit = dataConfig.getLimit();

    @GetMapping("/")
    public ResponseEntity<MediaSocialsResponse> getAll(
            @RequestParam(value = "_start", defaultValue = "0") int start,
            @RequestParam(value = "_limit", defaultValue = "0") int limit
    ) {
        System.out.println("MediaSocialController.getAll() start: " + start + ", limit: " + limit);
        if(start <= 0){
            start = this.start;
        }
        if(limit <= 0){
            limit = this.limit;
        }
        List<MediaSocial> mediaSocials = mediaSocialService.getAll(start, limit);
        System.out.println("MediaSocialController.getAll() mediaSocials: " + mediaSocials);
        MediaSocialsResponse mediaSocialsResponse = new MediaSocialsResponse();
        mediaSocialsResponse.setMediaSocialsResposeSuccess(mediaSocials, mediaSocialService.countAll(), "MediaSocials found");
        return new ResponseEntity<MediaSocialsResponse>(mediaSocialsResponse, HttpStatus.OK);
    }

    // @GetMapping("detail/{id}")
    // public ResponseEntity<Optional<MediaSocial>> getById(@PathVariable String id) {
    //     return new ResponseEntity<>(mediaSocialService.getById(id), HttpStatus.OK);
    // }

    @GetMapping("/detail/{slug}")
    public ResponseEntity<MediaSocialResponse> getBySlug(@PathVariable String slug) {
        System.out.println("MediaSocialController.getBySlug() slug: " + slug);
        MediaSocialResponse mediaSocialReponse = new MediaSocialResponse();
        Optional<MediaSocial> mediaSocial = mediaSocialService.getBySlug(slug);
        System.out.println("MediaSocialController.getBySlug() mediaSocial: " + mediaSocial);
        if(mediaSocial.isPresent()){
            mediaSocialReponse.setMediaSocialResposeSuccess(mediaSocial.get(), "MediaSocial found by slug");
            return new ResponseEntity<MediaSocialResponse>(mediaSocialReponse, HttpStatus.OK);
        }
        mediaSocialReponse.setMediaSocialResposeFail("MediaSocial not found");
        return new ResponseEntity<MediaSocialResponse>(mediaSocialReponse, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<MediaSocial> add(@RequestBody MediaSocial mediaSocial) {
        return new ResponseEntity<>(mediaSocialService.save(mediaSocial), HttpStatus.OK);
    }

    @PutMapping("/update/{id}/")
    public ResponseEntity<MediaSocial> update(@PathVariable String id, @RequestBody MediaSocial mediaSocial) {
        mediaSocial.setId(id);
        return new ResponseEntity<>(mediaSocialService.save(mediaSocial), HttpStatus.OK);
    }

    // @GetMapping("/search-by-title/")
    // public ResponseEntity<List<MediaSocial>> findItemByTitle(@RequestParam(value = "_title", defaultValue = "") String title) {
    //     System.out.println("MediaSocialController.findItemByTitle() title: " + title);
    //     return new ResponseEntity<>(mediaSocialService.findItemByTitle(title), HttpStatus.OK);
    // }

    @GetMapping("/search-filter/")
    public ResponseEntity<MediaSocialsResponse> searchByTitleAndFilter(
            @RequestParam(value = "_query", defaultValue = "") String title,
            @RequestParam(value = "_author_name", defaultValue = "") String author_name,
            @RequestParam(value = "_category_name", defaultValue = "") String category_name,
            @RequestParam(value = "_producer_name", defaultValue = "") String producer_name,
            @RequestParam(value = "_start", defaultValue = "0") int start,
            @RequestParam(value = "_limit", defaultValue = "0") int limit
    ) {
        System.out.println("MediaSocialController.searchByTitleAndFilter() title: " + title + ", author_name: " + author_name + ", category_name: " + category_name + ", producer_name: " + producer_name);
        if(start <= 0){
            start = this.start;
        }
        if(limit <= 0){
            limit = this.limit;
        }
        List<MediaSocial> mediaSocials = mediaSocialService.searchByTitleAndFilter(title, author_name, category_name, producer_name, start, limit);
        System.out.println("MediaSocialController.searchByTitleAndFilter() mediaSocials: " + mediaSocials);
        MediaSocialsResponse mediaSocialsResponse = new MediaSocialsResponse();
        mediaSocialsResponse.setMediaSocialsResposeSuccess(mediaSocials, mediaSocialService.countSearchByTitleAndFilter(title, author_name, category_name, producer_name), "MediaSocials found by title and filter");
        return new ResponseEntity<MediaSocialsResponse>(mediaSocialsResponse, HttpStatus.OK);
    }

    @GetMapping("/filter/")
    public ResponseEntity<MediaSocialsResponse> filterByAuthorAndCategoryAndProducerSlug(
            @RequestParam(value = "_author_name", defaultValue = "") String author_name,
            @RequestParam(value = "_category_name", defaultValue = "") String category_name,
            @RequestParam(value = "_producer_name", defaultValue = "") String producer_name,
            @RequestParam(value = "_start", defaultValue = "0") int start,
            @RequestParam(value = "_limit", defaultValue = "0") int limit
    ) {
        System.out.println("MediaSocialController.filterByAuthorAndCategoryAndProducerSlug() author_name: " + author_name + ", category_name: " + category_name + ", producer_name: " + producer_name);
        if(start <= 0){
            start = this.start;
        }
        if(limit <= 0){
            limit = this.limit;
        }
        List<MediaSocial> mediaSocials = mediaSocialService.filterByAuthorAndCategoryAndProducerSlug(author_name, category_name, producer_name, start, limit);
        System.out.println("MediaSocialController.filterByAuthorAndCategoryAndProducerSlug() mediaSocials: " + mediaSocials);
        MediaSocialsResponse mediaSocialsResponse = new MediaSocialsResponse();
        mediaSocialsResponse.setMediaSocialsResposeSuccess(mediaSocials, mediaSocialService.countByAuthorAndCategoryAndProducerSlug(author_name, category_name, producer_name), "MediaSocials found by author, category, producer");
        return new ResponseEntity<MediaSocialsResponse>(mediaSocialsResponse, HttpStatus.OK);
    }
    

    @DeleteMapping("/delete/{id}/")
    public void deleteById(@PathVariable String id) {
        mediaSocialService.deleteById(id);
    }
    
    
}
