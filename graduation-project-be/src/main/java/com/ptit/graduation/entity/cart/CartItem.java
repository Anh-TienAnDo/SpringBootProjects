package com.ptit.graduation.entity.cart;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    // Map<ProductId, quantity>
    // private Map<String, Integer> items = new HashMap<>();
    private String productId;
    private Integer quantity;
    private String productName;
    private Long price;

}
