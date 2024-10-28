package com.ptit.product_search.service.product.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.product_search.entity.product.LocationEntity;
import com.ptit.product_search.repository.product.LocationRepository;
import com.ptit.product_search.service.product.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired 
    private LocationRepository locationRepository;

    @Override
    public LocationEntity create(LocationEntity t) {
        // TODO Auto-generated method stub
        return locationRepository.save(t);
    }

    @Override
    public LocationEntity update(LocationEntity t) {
        // TODO Auto-generated method stub
        return locationRepository.save(t);
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        locationRepository.deleteById(id);
    }

    @Override
    public LocationEntity get(String id) {
        // TODO Auto-generated method stub
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public List<LocationEntity> list() {
        // TODO Auto-generated method stub
        return locationRepository.findAll();
    }

    @Override
    public LocationEntity findBySlug(String slug) {
        // TODO Auto-generated method stub
        return locationRepository.findBySlug(slug);
    }

    @Override
    public LocationEntity findBySlugAndType(String slug, String type) {
        // TODO Auto-generated method stub
        return locationRepository.findBySlugAndType(slug, type);
    }
    
}
