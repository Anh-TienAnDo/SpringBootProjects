package app.e_commerce_application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.e_commerce_application.entities.Author;
import app.e_commerce_application.payloads.AuthorsResponse;
import app.e_commerce_application.services.AuthorCategoryProducerService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorCategoryProducerService authorService;

    @GetMapping("")
    public ResponseEntity<AuthorsResponse> getAll() {
        System.out.println("AuthorController.getAll()");
        AuthorsResponse authorsReponse = new AuthorsResponse();
        List<Author> authors = authorService.getAllAuthors();
        System.out.println("AuthorController.getAll() authors: " + authors);
        authorsReponse.setAuthorResposeSuccess(authors, "Authors found");
        return new ResponseEntity<AuthorsResponse>(authorsReponse, HttpStatus.OK);
    }
    
}
