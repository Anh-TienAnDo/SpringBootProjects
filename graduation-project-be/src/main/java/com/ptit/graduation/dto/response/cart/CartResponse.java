package com.ptit.graduation.dto.response.cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.ptit.graduation.entity.cart.CartItem;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class CartResponse {
    private List<CartItem> cart;
    private long total;
}
