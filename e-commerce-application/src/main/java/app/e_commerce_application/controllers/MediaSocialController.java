package app.e_commerce_application.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.e_commerce_application.entities.MediaSocial;
import app.e_commerce_application.entities.Detail;
import app.e_commerce_application.services.MediaSocialService;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/media-socials")
public class MediaSocialController {

    @Autowired
    private MediaSocialService mediaSocialService;
    
    @GetMapping("/all")
    public ResponseEntity<List<MediaSocial>> getAll() {
        // return mediaSocialService.getAll();
        return new ResponseEntity<>(mediaSocialService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MediaSocial>> getById(@PathVariable String id) {
        return new ResponseEntity<>(mediaSocialService.getById(id), HttpStatus.OK);
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

    @GetMapping("/search")
    public List<MediaSocial> getByTitle(@RequestParam String title) {
        return mediaSocialService.getByTitle(title);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id) {
        mediaSocialService.deleteById(id);
    }
    
    
}
