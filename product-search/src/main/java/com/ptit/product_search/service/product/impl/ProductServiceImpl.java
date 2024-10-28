package com.ptit.product_search.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.product_search.entity.product.BrandEntity;
import com.ptit.product_search.entity.product.CategoryEntity;
import com.ptit.product_search.entity.product.LocationEntity;
import com.ptit.product_search.entity.product.ProductEntity;
import com.ptit.product_search.repository.product.ProductRepository;
import com.ptit.product_search.service.product.BrandService;
import com.ptit.product_search.service.product.CategoryService;
import com.ptit.product_search.service.product.LocationService;
import com.ptit.product_search.service.product.ProductService;
import com.ptit.product_search.utils.ConvertVietnameseToNormalText;

@Service
public class ProductServiceImpl implements ProductService {
    private ConvertVietnameseToNormalText convertVietnameseToNormalText = new ConvertVietnameseToNormalText();

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private LocationService locationService;

    @Override
    public ProductEntity create(ProductEntity productEntity) {

        // check category
        checkCategory(productEntity);

        // check brand
        checkBrand(productEntity);

        // check location
        checkLocation(productEntity);

        return productRepository.save(productEntity);
    }

    @Override
    public ProductEntity update(ProductEntity productEntity) {
        // Implementation here
        // check category
        checkCategory(productEntity);

        // check brand
        checkBrand(productEntity);

        // check location
        checkLocation(productEntity);

        return productRepository.save(productEntity);
    }

    private void checkCategory(ProductEntity productEntity) {
        // Implementation here
        List<String> categoriesName = productEntity.getDetails().getCategoriesSlug();
        if(categoriesName != null) {
            for(String categoryName : categoriesName) {
                // check category
                String categorySlug = convertVietnameseToNormalText.slugify(categoryName);
                // check category exist
                if(categoryService.findBySlugAndType(categorySlug, productEntity.getType()) == null) {
                    // create new category
                    CategoryEntity categoryEntity = new CategoryEntity();
                    categoryEntity.setName(categoryName);
                    categoryEntity.setSlug(categorySlug);
                    categoryEntity.setType(productEntity.getType());
                    categoryService.create(categoryEntity);
                }

            }
        }
    }

    private void checkBrand(ProductEntity productEntity) {
        // Implementation here
        String BrandName = productEntity.getDetails().getBrandSlug();
        if(BrandName != null) {
            // check brand
            String brandSlug = convertVietnameseToNormalText.slugify(BrandName);
            // check brand exist
            if(brandService.findBySlugAndType(brandSlug, productEntity.getType()) == null) {
                // create new brand
                BrandEntity brandEntity = new BrandEntity();
                brandEntity.setName(BrandName);
                brandEntity.setSlug(brandSlug);
                brandEntity.setType(productEntity.getType());
                brandService.create(brandEntity);
            }
        }
    }

    private void checkLocation(ProductEntity productEntity) {
        // Implementation here
        List<String> locationName = productEntity.getDetails().getLocations();
        if(locationName != null) {
            // check location
            for(String location : locationName) {
                String locationSlug = convertVietnameseToNormalText.slugify(location);
                // check location exist
                if(locationService.findBySlugAndType(locationSlug, productEntity.getType()) == null) {
                    // create new location
                    LocationEntity locationEntity = new LocationEntity();
                    locationEntity.setName(location);
                    locationEntity.setSlug(locationSlug);
                    locationEntity.setType(productEntity.getType());
                    locationService.create(locationEntity);
                }
            }
        }
    }

    @Override
    public void delete(String id) {
        // Implementation here
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductEntity> list() {
        // Implementation here
        return productRepository.findAll();
    }


    @Override
    public ProductEntity get(String id) {
        // Implementation here
        return productRepository.findById(id).orElse(null);
    }
}
