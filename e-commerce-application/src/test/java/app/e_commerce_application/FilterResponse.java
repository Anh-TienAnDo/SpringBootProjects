// package app.e_commerce_application;
// import static org.mockito.ArgumentMatchers.notNull;

// import java.util.ArrayList;
// import java.util.List;

// import org.bson.Document;
// import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.data.mongodb.repository.Query;

// import com.mongodb.client.MongoClient;
// import com.mongodb.client.MongoClients;
// import com.mongodb.client.MongoCollection;
// import com.mongodb.client.MongoDatabase;
// import com.mongodb.client.model.fill.*;
// import com.mongodb.client.model.search.*;

// public class test_mongodb {
//     public static void main(String[] args) {
//         System.out.println("Hello, World!");
//         String uri = "mongodb://localhost:27017";
//         String database_name = "mydatabase";
//         String collection_name = "inventory";

//         // kết nối tới MongoDB
//         MongoClient mongoClient = MongoClients.create(uri);
//         MongoDatabase database = mongoClient.getDatabase(database_name);
//         MongoCollection<Document> collection = database.getCollection(collection_name);
//         System.out.println("Connected to the database");
//         List<Document> studentList = collection.find().limit(3).into(new ArrayList<>());
//         for (Document student : studentList) {
//             System.out.println(student.toJson());
//         }

//     }
// }
// -----------------------------------

// package app.e_commerce_application.controllers;

// import app.e_commerce_application.entities.MediaSocial;
// import app.e_commerce_application.services.MediaSocialService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/media-socials")
// public class MediaSocialController {

//     @Autowired
//     private MediaSocialService mediaSocialService;

//     @GetMapping("/all")
//     public ResponseEntity<List<MediaSocial>> getAll() {
//         return new ResponseEntity<>(mediaSocialService.getAll(), HttpStatus.OK);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Optional<MediaSocial>> getById(@PathVariable String id) {
//         return new ResponseEntity<>(mediaSocialService.getById(id), HttpStatus.OK);
//     }

//     @PostMapping("/add")
//     public ResponseEntity<MediaSocial> add(@RequestBody MediaSocial mediaSocial) {
//         return new ResponseEntity<>(mediaSocialService.save(mediaSocial), HttpStatus.OK);
//     }

//     @PutMapping("/update/{id}")
//     public ResponseEntity<MediaSocial> update(@PathVariable String id, @RequestBody MediaSocial mediaSocial) {
//         mediaSocial.setId(id);
//         return new ResponseEntity<>(mediaSocialService.save(mediaSocial), HttpStatus.OK);
//     }

//     @GetMapping("/filter")
//     public ResponseEntity<?> filter(
//             @RequestParam(value = "_start", defaultValue = "0") int start,
//             @RequestParam(value = "_limit", defaultValue = "12") int limit,
//             @RequestParam(value = "_author_slug", defaultValue = "all") String authorSlug,
//             @RequestParam(value = "_category_slug", defaultValue = "all") String categorySlug) {

//         List<MediaSocial> mediaSocials = mediaSocialService.filter(authorSlug, categorySlug, start, limit);
//         if (mediaSocials.isEmpty()) {
//             return new ResponseEntity<>("No data found", HttpStatus.NOT_FOUND);
//         }

//         long total = mediaSocialService.countFiltered(authorSlug, categorySlug);
//         return new ResponseEntity<>(new FilterResponse(total, mediaSocials), HttpStatus.OK);
//     }

//     @GetMapping("/search")
//     public ResponseEntity<?> searchByTitle(
//             @RequestParam(value = "_start", defaultValue = "0") int start,
//             @RequestParam(value = "_limit", defaultValue = "12") int limit,
//             @RequestParam(value = "_query", defaultValue = "") String query) {

//         List<MediaSocial> mediaSocials = mediaSocialService.searchByTitle(query, start, limit);
//         if (mediaSocials.isEmpty()) {
//             return new ResponseEntity<>("No data found", HttpStatus.NOT_FOUND);
//         }

//         return new ResponseEntity<>(mediaSocials, HttpStatus.OK);
//     }
// }


// --------------------------------
// package app.e_commerce_application.services;

// import app.e_commerce_application.entities.MediaSocial;
// import app.e_commerce_application.repositories.MediaSocialRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class MediaSocialService {

//     @Autowired
//     private MediaSocialRepository mediaSocialRepository;

//     public List<MediaSocial> getAll() {
//         return mediaSocialRepository.findAll();
//     }

//     public Optional<MediaSocial> getById(String id) {
//         return mediaSocialRepository.findById(id);
//     }

//     public MediaSocial save(MediaSocial mediaSocial) {
//         return mediaSocialRepository.save(mediaSocial);
//     }

//     public void deleteById(String id) {
//         mediaSocialRepository.deleteById(id);
//     }

//     public List<MediaSocial> filter(String authorSlug, String categorySlug, int start, int limit) {
//         if (!authorSlug.equals("all") && !categorySlug.equals("all")) {
//             return mediaSocialRepository.findByAuthorSlugAndCategorySlug(authorSlug, categorySlug, start, limit);
//         } else if (!authorSlug.equals("all")) {
//             return mediaSocialRepository.findByAuthorSlug(authorSlug, start, limit);
//         } else if (!categorySlug.equals("all")) {
//             return mediaSocialRepository.findByCategorySlug(categorySlug, start, limit);
//         } else {
//             return mediaSocialRepository.findAllActive(start, limit);
//         }
//     }

//     public long countFiltered(String authorSlug, String categorySlug) {
//         if (!authorSlug.equals("all") && !categorySlug.equals("all")) {
//             return mediaSocialRepository.countByAuthorSlugAndCategorySlug(authorSlug, categorySlug);
//         } else if (!authorSlug.equals("all")) {
//             return mediaSocialRepository.countByAuthorSlug(authorSlug);
//         } else if (!categorySlug.equals("all")) {
//             return mediaSocialRepository.countByCategorySlug(categorySlug);
//         } else {
//             return mediaSocialRepository.countAllActive();
//         }
//     }

//     public List<MediaSocial> searchByTitle(String query, int start, int limit) {
//         return mediaSocialRepository.findByTitleContaining(query, start, limit);
//     }
// }

// -----------------------------------
// package app.e_commerce_application.repositories;

// import app.e_commerce_application.entities.MediaSocial;
// import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.data.mongodb.repository.Query;
// import org.springframework.stereotype.Repository;

// import java.util.List;

// @Repository
// public interface MediaSocialRepository extends MongoRepository<MediaSocial, String> {

//     @Query("{ 'detail.author.slug': ?0, 'detail.category.slug': ?1, 'is_active': true }")
//     List<MediaSocial> findByAuthorSlugAndCategorySlug(String authorSlug, String categorySlug, int start, int limit);

//     @Query("{ 'detail.author.slug': ?0, 'is_active': true }")
//     List<MediaSocial> findByAuthorSlug(String authorSlug, int start, int limit);

//     @Query("{ 'detail.category.slug': ?0, 'is_active': true }")
//     List<MediaSocial> findByCategorySlug(String categorySlug, int start, int limit);

//     @Query("{ 'is_active': true }")
//     List<MediaSocial> findAllActive(int start, int limit);

//     @Query(value = "{ 'detail.author.slug': ?0, 'detail.category.slug': ?1, 'is_active': true }", count = true)
//     long countByAuthorSlugAndCategorySlug(String authorSlug, String categorySlug);

//     @Query(value = "{ 'detail.author.slug': ?0, 'is_active': true }", count = true)
//     long countByAuthorSlug(String authorSlug);

//     @Query(value = "{ 'detail.category.slug': ?0, 'is_active': true }", count = true)
//     long countByCategorySlug(String categorySlug);

//     @Query(value = "{ 'is_active': true }", count = true)
//     long countAllActive();

//     @Query("{ 'title': { $regex: ?0, $options: 'i' }, 'is_active': true }")
//     List<MediaSocial> findByTitleContaining(String query, int start, int limit);
// }

// -----------------------------------
// package app.e_commerce_application.controllers;

// import app.e_commerce_application.entities.MediaSocial;

// import java.util.List;

// public class FilterResponse {
//     private long total;
//     private List<MediaSocial> mediaSocials;

//     public FilterResponse(long total, List<MediaSocial> mediaSocials) {
//         this.total = total;
//         this.mediaSocials = mediaSocials;
//     }

//     // Getters and Setters
// }

// Controller: MediaSocialController chứa các phương thức để xử lý các yêu cầu GET với các tham số truy vấn và trả về kết quả dưới dạng JSON.
// Service: MediaSocialService chứa logic nghiệp vụ để thực hiện các truy vấn MongoDB.
// Repository: MediaSocialRepository chứa các phương thức truy vấn MongoDB sử dụng @Query.
// FilterResponse: Lớp này được sử dụng để trả về kết quả của phương thức filter với tổng số kết quả và danh sách các đối tượng MediaSocial.