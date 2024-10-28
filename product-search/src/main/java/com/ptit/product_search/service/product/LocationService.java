package com.ptit.product_search.service.product;

import com.ptit.product_search.entity.product.LocationEntity;
import com.ptit.product_search.service.base.BaseService;

public interface LocationService extends BaseService<LocationEntity> {

    LocationEntity findBySlug(String slug);

    LocationEntity findBySlugAndType(String slug, String type);
    
}
