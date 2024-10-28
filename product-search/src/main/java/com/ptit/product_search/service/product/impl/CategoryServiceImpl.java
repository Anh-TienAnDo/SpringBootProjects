package com.ptit.product_search.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.product_search.entity.product.CategoryEntity;
import com.ptit.product_search.repository.product.CategoryRepository;
import com.ptit.product_search.service.product.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryEntity create(CategoryEntity t) {
        // Implementation here
        return categoryRepository.save(t);
    }

    @Override
    public void delete(String id) {
        // Implementation here
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryEntity> list() {
        // Implementation here
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity update(CategoryEntity t) {
        // Implementation here
        return categoryRepository.save(t);
    }

    @Override
    public CategoryEntity get(String id) {
        // Implementation here
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public CategoryEntity findBySlug(String slug) {
        // TODO Auto-generated method stub
        return categoryRepository.findBySlug(slug);
    }

    @Override
    public CategoryEntity findBySlugAndType(String slug, String type) {
        // TODO Auto-generated method stub
        return categoryRepository.findBySlugAndType(slug, type);
    }
}
