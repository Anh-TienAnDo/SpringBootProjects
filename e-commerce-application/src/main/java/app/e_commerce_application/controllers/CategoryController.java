package app.e_commerce_application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.e_commerce_application.payloads.CategoriesResponse;
import app.e_commerce_application.services.AuthorCategoryProducerService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private AuthorCategoryProducerService categoryService;

    @GetMapping("")
    public ResponseEntity<CategoriesResponse> getAll() {
        CategoriesResponse categoriesReponse = new CategoriesResponse();
        categoriesReponse.setCategoryResposeSuccess(categoryService.getAllCategories(), "Categories found");
        return new ResponseEntity<CategoriesResponse>(categoriesReponse, HttpStatus.OK);
    }
    
}
