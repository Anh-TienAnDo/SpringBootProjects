package com.ptit.product_search.entity.product;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ptit.product_search.entity.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.ptit.product_search.utils.ConvertVietnameseToNormalText;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Product")
// search by slug, filter by type + slug title/brand/category
public class ProductEntity extends BaseEntity {

    @Field(name = "title")
    private String title;

    @Field(name = "slug")
    private String slug;

    @Field(name = "type")
    private String type;

    @Field(name = "view")
    private Integer view = 0;

    @Field(name = "price_new")
    private Integer priceNew = 0;

    @Field(name = "price_old")
    private Integer priceOld = 0;

    @Field(name = "image")
    private String image;

    @Field(name = "is_active")
    private boolean isActive = true;

    @Field(name = "is_hot")
    private boolean isHot = false;

    @Field(name = "description")
    private String description;
    
    @Field(name = "details")
    private Details details;

    @Field(name = "review")
    private Review review;

    public void setTitle(String title) {
        ConvertVietnameseToNormalText convertVietnameseToNormalText = new ConvertVietnameseToNormalText();
        this.title = title;
        this.slug = convertVietnameseToNormalText.slugify(title);
    }

    public static void main(String[] args) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setTitle("title");
        productEntity.setSlug("slug");
        productEntity.setType("type");
        productEntity.setView(0);
        productEntity.setPriceNew(0);
        productEntity.setPriceOld(0);
        productEntity.setImage("image");
        productEntity.setActive(true);
        productEntity.setHot(false);
        productEntity.setDetails(new Details());
    }
    
}


