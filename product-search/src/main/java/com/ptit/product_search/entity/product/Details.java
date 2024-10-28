package com.ptit.product_search.entity.product;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "details")
public class Details {

    // @Field(name = "author_slug")
    // private String authorslug;

    @Field(name = "categories_slug")
    private List<String> categoriesSlug;

    // @Field(name = "made_in_slug")
    // private String madeInSlug;

    @Field(name = "brand_slug")
    private String brandSlug;

    @Field(name = "locations")
    private List<String> locations;

    
    @Field(name = "quantity")
    private int quantity;

    
    public static void main(String[] args) {
        Details details = new Details();
        details.setLocations(List.of("location"));
        details.setQuantity(1);
        System.out.println(details);
    }
}
