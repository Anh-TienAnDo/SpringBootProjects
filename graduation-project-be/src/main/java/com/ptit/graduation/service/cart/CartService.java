package com.ptit.graduation.service.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import com.ptit.graduation.dto.response.cart.CartResponse;
import com.ptit.graduation.entity.cart.CartItem;
import com.ptit.graduation.entity.product.ProductMongo;
import com.ptit.graduation.service.product.ProductMongoService;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import static com.ptit.graduation.constants.GraduationProjectConstants.CommonConstants.*;

@Slf4j
@Service
public class CartService {
    // private final String CART_KEY_PREFIX = "cart:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ProductMongoService productMongoService;

    public void addItemToCart(String userId, String productId, int quantity) {
        redisTemplate.opsForHash().put(CART_KEY_PREFIX + userId, productId, quantity);
    }

    public CartResponse getCartItems(String userId) {
        Map<Object, Object> items = redisTemplate.opsForHash().entries(CART_KEY_PREFIX + userId);
        List<CartItem> cartItems = new ArrayList<>();
        items.forEach((key, value) -> {
            String productId = (String) key;
            Integer price = (Integer) value;
            ProductMongo product = (ProductMongo) redisTemplate.opsForHash().get(PRODUCT_HASH_KEY, productId);
            if (product == null) {
                product = productMongoService.get(productId);
                // redisTemplate.opsForHash().put(PRODUCT_HASH_KEY, productId, product);
            }
            CartItem cartItem = new CartItem();
            cartItem.setProductId(productId);
            cartItem.setQuantity(price);
            cartItem.setProductName(product.getName());
            cartItem.setPrice(product.getSellingPrice());
            cartItems.add(cartItem);
        });
        return CartResponse.of(cartItems, cartItems.size());
    }

    public void removeItemFromCart(String userId, String productId) {
        redisTemplate.opsForHash().delete(CART_KEY_PREFIX + userId, productId);
    }

    public void updateItemInCart(String userId, String productId, int quantity) {
        redisTemplate.opsForHash().put(CART_KEY_PREFIX + userId, productId, quantity);
    }

    public void increaseItemQuantity(String userId, String productId) {
        Integer quantity = (Integer) redisTemplate.opsForHash().get(CART_KEY_PREFIX + userId, productId);
        if (quantity != null) {
            redisTemplate.opsForHash().put(CART_KEY_PREFIX + userId, productId, quantity + 1);
        }
    }

    public void decreaseItemQuantity(String userId, String productId) {
        Integer quantity = (Integer) redisTemplate.opsForHash().get(CART_KEY_PREFIX + userId, productId);
        if (quantity != null && quantity > 1) {
            redisTemplate.opsForHash().put(CART_KEY_PREFIX + userId, productId, quantity - 1);
        }
    }

    public void clearCart(String userId) {
        redisTemplate.delete(CART_KEY_PREFIX + userId);
    }
    
}
