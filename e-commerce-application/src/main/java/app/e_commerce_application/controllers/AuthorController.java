package app.e_commerce_application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.e_commerce_application.payloads.AuthorsResponse;
import app.e_commerce_application.services.AuthorCategoryProducerService;
import jakarta.persistence.Entity;

@Entity
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorCategoryProducerService authorService;

    @GetMapping("/")
    public ResponseEntity<AuthorsResponse> getAll() {
        AuthorsResponse authorsReponse = new AuthorsResponse();
        authorsReponse.setAuthorResposeSuccess(authorService.getAllAuthors(), "Authors found");
        return new ResponseEntity<AuthorsResponse>(authorsReponse, HttpStatus.OK);
    }
    
}
