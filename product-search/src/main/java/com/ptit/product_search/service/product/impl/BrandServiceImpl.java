package com.ptit.product_search.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.product_search.entity.product.BrandEntity;
import com.ptit.product_search.repository.product.BrandRepository;
import com.ptit.product_search.service.product.BrandService;

@Service
public class BrandServiceImpl implements BrandService{
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public BrandEntity create(BrandEntity t) {
        // TODO Auto-generated method stub
        return brandRepository.save(t);
    }

    @Override
    public BrandEntity update(BrandEntity t) {
        // TODO Auto-generated method stub
        return brandRepository.save(t);
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        brandRepository.deleteById(id);
    }

    @Override
    public BrandEntity get(String id) {
        // TODO Auto-generated method stub
        return brandRepository.findById(id).orElse(null);
    }

    @Override
    public List<BrandEntity> list() {
        // TODO Auto-generated method stub
        return brandRepository.findAll();
    }

    @Override
    public BrandEntity findBySlug(String slug) {
        // TODO Auto-generated method stub
        return brandRepository.findBySlug(slug);
    }

    @Override
    public BrandEntity findBySlugAndType(String slug, String type) {
        // TODO Auto-generated method stub
        return brandRepository.findBySlugAndType(slug, type);
    }
    
}
