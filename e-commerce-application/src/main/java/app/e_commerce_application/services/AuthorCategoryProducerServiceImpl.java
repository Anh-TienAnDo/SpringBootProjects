package app.e_commerce_application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.e_commerce_application.entities.Author;
import app.e_commerce_application.entities.Category;
import app.e_commerce_application.entities.Producer;
import app.e_commerce_application.repositories.AuthorRepository;
import app.e_commerce_application.repositories.CategoryRepository;
import app.e_commerce_application.repositories.ProducerRepository;

@Service
public class AuthorCategoryProducerServiceImpl implements AuthorCategoryProducerService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProducerRepository producerRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.getAll();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.getAll();
    }

    @Override
    public List<Producer> getAllProducers() {
        return producerRepository.getAll();
    }

    @Override
    public Optional<Author> getAuthorBySlug(String slug) {
        return authorRepository.getBySlug(slug);
    }

    @Override
    public Optional<Category> getCategoryBySlug(String slug) {
        return categoryRepository.getBySlug(slug);
    }

    @Override
    public Optional<Producer> getProducerBySlug(String slug) {
        return producerRepository.getBySlug(slug);
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Producer saveProducer(Producer producer) {
        return producerRepository.save(producer);
    }

    @Override
    public void deleteAuthorById(String id) {
        authorRepository.deleteById(id);
    }

    @Override
    public void deleteCategoryById(String id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteProducerById(String id) {
        producerRepository.deleteById(id);
    }
    
}
