package com.ptit.product_search.service.product;


import com.ptit.product_search.entity.product.CategoryEntity;
import com.ptit.product_search.service.base.BaseService;

public interface CategoryService extends BaseService<CategoryEntity>{
    
    CategoryEntity findBySlug(String slug);

    CategoryEntity findBySlugAndType(String slug, String type);
    
}
