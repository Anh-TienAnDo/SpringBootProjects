package app.e_commerce_application.services;

import java.util.List;
import java.util.Optional;

import app.e_commerce_application.entities.Author;
import app.e_commerce_application.entities.Category;
import app.e_commerce_application.entities.Producer;

public interface AuthorCategoryProducerService {

    public List<Author> getAllAuthors();

    public List<Category> getAllCategories();

    public List<Producer> getAllProducers();

    public Optional<Author> getAuthorBySlug(String slug);

    public Optional<Category> getCategoryBySlug(String slug);

    public Optional<Producer> getProducerBySlug(String slug);

    public Author saveAuthor(Author author);

    public Category saveCategory(Category category);

    public Producer saveProducer(Producer producer);

    public void deleteAuthorById(String id);

    public void deleteCategoryById(String id);

    public void deleteProducerById(String id);
    
} 
