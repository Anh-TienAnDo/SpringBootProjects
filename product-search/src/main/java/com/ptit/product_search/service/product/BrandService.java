package com.ptit.product_search.service.product;

import com.ptit.product_search.entity.product.BrandEntity;
import com.ptit.product_search.service.base.BaseService;

public interface BrandService extends BaseService<BrandEntity>{

    BrandEntity findBySlug(String slug);

    BrandEntity findBySlugAndType(String slug, String type);
    
}
