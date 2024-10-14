package app.e_commerce_application.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.e_commerce_application.entities.MediaSocial;
import app.e_commerce_application.payloads.MediaSocialResponse;
import app.e_commerce_application.payloads.MediaSocialsResponse;
import app.e_commerce_application.security.JWTFilter;
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

    protected JWTFilter jwtFilter = new JWTFilter();

    private Data dataConfig = new Data();
    private Integer start = dataConfig.getStart();
    private Integer limit = dataConfig.getLimit();

    // /api/media-socials?_type=sayings&_start=0&_limit=0
    @GetMapping("")
    public ResponseEntity<MediaSocialsResponse> getAll(
        @RequestParam(value = "_type", defaultValue = "sayings") String type,
        @RequestParam(value = "_start", defaultValue = "0") int start,
        @RequestParam(value = "_limit", defaultValue = "0") int limit,
        @RequestHeader(value = "Authorization", defaultValue = "") String token
    ) {
        if(jwtFilter.doFilterUser(token)){
            System.out.println("MediaSocialController.getAll() token: " + token);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        System.out.println("MediaSocialController.getAll() type: " + type + ", start: " + start + ", limit: " + limit);
        if(start <= 0){
            start = this.start;
        }
        if(limit <= 0){
            limit = this.limit;
        }
        List<MediaSocial> mediaSocials = mediaSocialService.getAll(type, start, limit);
        System.out.println("MediaSocialController.getAll() mediaSocials: " + mediaSocials);
        MediaSocialsResponse mediaSocialsResponse = new MediaSocialsResponse();
        mediaSocialsResponse.setMediaSocialsResposeSuccess(mediaSocials, mediaSocialService.countAll(type), "MediaSocials found");
        return new ResponseEntity<MediaSocialsResponse>(mediaSocialsResponse, HttpStatus.OK);
    }

    // @GetMapping("detail/{id}")
    // public ResponseEntity<Optional<MediaSocial>> getById(@PathVariable String id) {
    //     return new ResponseEntity<>(mediaSocialService.getById(id), HttpStatus.OK);
    // }

    // /api/media-socials/details/sayings/abc
    @GetMapping("/details/{type}/{slug}")
    public ResponseEntity<MediaSocialResponse> getByTypeAndSlug(@PathVariable String type, @PathVariable String slug) {
        System.out.println("MediaSocialController.getBySlug() type: " + type + ", slug: " + slug);
        MediaSocialResponse mediaSocialReponse = new MediaSocialResponse();
        Optional<MediaSocial> mediaSocial = mediaSocialService.getByTypeAndSlug(type, slug);
        System.out.println("MediaSocialController.getByTypeAndSlug() mediaSocial: " + mediaSocial);
        if(mediaSocial.isPresent()){
            mediaSocialReponse.setMediaSocialResposeSuccess(mediaSocial.get(), "MediaSocial found by type and slug");
            return new ResponseEntity<MediaSocialResponse>(mediaSocialReponse, HttpStatus.OK);
        }
        mediaSocialReponse.setMediaSocialResposeFail("MediaSocial not found");
        return new ResponseEntity<MediaSocialResponse>(mediaSocialReponse, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<MediaSocial> add(@RequestBody MediaSocial mediaSocial) {
        return new ResponseEntity<>(mediaSocialService.save(mediaSocial), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MediaSocial> update(@PathVariable String id, @RequestBody MediaSocial mediaSocial) {
        mediaSocial.setId(id);
        return new ResponseEntity<>(mediaSocialService.save(mediaSocial), HttpStatus.OK);
    }

    // @GetMapping("/search-by-title/")
    // public ResponseEntity<List<MediaSocial>> findItemByTitle(@RequestParam(value = "_title", defaultValue = "") String title) {
    //     System.out.println("MediaSocialController.findItemByTitle() title: " + title);
    //     return new ResponseEntity<>(mediaSocialService.findItemByTitle(title), HttpStatus.OK);
    // }

    // /api/media-socials/search-filter?_query=abc&_author_name=abc&_category_name=abc&_producer_name=abc&_type=abc&_time_total=0&_start=0&_limit=0
    @GetMapping("/search-filter")
    public ResponseEntity<MediaSocialsResponse> searchBySlugAndFilter(
            @RequestParam(value = "_query", defaultValue = "") String query,
            @RequestParam(value = "_author_name", defaultValue = "") String author_name,
            @RequestParam(value = "_category_name", defaultValue = "") String category_name,
            @RequestParam(value = "_producer_name", defaultValue = "") String producer_name,
            @RequestParam(value = "_type", defaultValue = "") String type,
            @RequestParam(value = "_time_total", defaultValue = "0") int time_total,
            @RequestParam(value = "_start", defaultValue = "0") int start,
            @RequestParam(value = "_limit", defaultValue = "0") int limit
    ) {
        System.out.println("MediaSocialController.searchByTitleAndFilter() query: " + query + ", author_name: " + author_name + ", category_name: " + category_name + ", producer_name: " + producer_name + ", time_total: " + time_total + ", start: " + start + ", limit: " + limit);
        System.out.println("MediaSocialController.searchByTitleAndFilter() type: " + type);
        if(start <= 0){
            start = this.start;
        }
        if(limit <= 0){
            limit = this.limit;
        }

        List<MediaSocial> mediaSocials = mediaSocialService.searchBySlugAndFilter(query, author_name, category_name, producer_name, type, time_total, start, limit);
        System.out.println("MediaSocialController.searchByTitleAndFilter() mediaSocials: " + mediaSocials);
        MediaSocialsResponse mediaSocialsResponse = new MediaSocialsResponse();
        mediaSocialsResponse.setMediaSocialsResposeSuccess(mediaSocials, mediaSocialService.countSearchBySlugAndFilter(query, author_name, category_name, producer_name, type, time_total), "MediaSocials found by title and filter");
        return new ResponseEntity<MediaSocialsResponse>(mediaSocialsResponse, HttpStatus.OK);
    }

    // /api/media-socials/filter?_type=abc&_author_name=abc&_category_name=abc&_producer_name=abc&_time_total=0&_start=0&_limit=0
    @GetMapping("/filter")
    public ResponseEntity<MediaSocialsResponse> filterByTypeAndAuthorAndCategoryAndProducerSlug(
            @RequestParam(value = "_type", defaultValue = "") String type,
            // @RequestParam(value = "_type", defaultValue = "sayings") String type,
            @RequestParam(value = "_author_name", defaultValue = "") String author_name,
            @RequestParam(value = "_category_name", defaultValue = "") String category_name,
            @RequestParam(value = "_producer_name", defaultValue = "") String producer_name,
            @RequestParam(value = "_time_total", defaultValue = "0") int time_total,
            @RequestParam(value = "_start", defaultValue = "0") int start,
            @RequestParam(value = "_limit", defaultValue = "0") int limit,
            @RequestHeader(value = "Authorization", defaultValue = "") String token
    ) {
        System.out.println("token: " + token);
        if(jwtFilter.doFilterUser(token)){
            System.out.println("MediaSocialController.getAll() token: " + token);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if(start <= 0){
            start = this.start;
        }
        if(limit <= 0){
            limit = this.limit;
        }
        System.out.println("MediaSocialController.filterByAuthorAndCategoryAndProducerSlug() author_name: " + author_name + ", category_name: " + category_name + ", producer_name: " + producer_name + ", start: " + start + ", limit: " + limit);
        System.out.println("MediaSocialController.filterByAuthorAndCategoryAndProducerSlug() type: " + type);
        List<MediaSocial> mediaSocials = mediaSocialService.filterByTypeAndAuthorAndCategoryAndProducerSlugAndTimeTotal(type, author_name, category_name, producer_name, time_total, start, limit);
        System.out.println("MediaSocialController.filterByTypeAndAuthorAndCategoryAndProducerSlug() mediaSocials: " + mediaSocials);
        MediaSocialsResponse mediaSocialsResponse = new MediaSocialsResponse();
        mediaSocialsResponse.setMediaSocialsResposeSuccess(mediaSocials, mediaSocialService.countByTypeAndAuthorAndCategoryAndProducerSlugAndTimeTotal(type, author_name, category_name, producer_name, time_total), "MediaSocials found by author, category, producer");
        return new ResponseEntity<MediaSocialsResponse>(mediaSocialsResponse, HttpStatus.OK);
    }
    

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id) {
        mediaSocialService.deleteById(id);
    }
    
    
}
