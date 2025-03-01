package com.ptit.graduation.controller.order;

import static com.ptit.graduation.constants.GraduationProjectConstants.MessageCode.SUCCESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ptit.graduation.dto.response.ResponseGeneral;
import com.ptit.graduation.dto.response.cart.CartResponse;
import com.ptit.graduation.service.cart.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping()
    public ResponseGeneral<CartResponse> list(){
        log.info("carts");
        String userId = "anh";
        return ResponseGeneral.ofSuccess(SUCCESS, cartService.getCartItems(userId));
    }

    @GetMapping("/add")
    public RedirectView add(
            @RequestParam String productId, @RequestParam int quantity, @RequestParam String returnUrl) {
        log.info("add cart");
        String userId = "anh";
        cartService.addItemToCart(userId, productId, quantity);
        return new RedirectView(returnUrl);
    }

    @DeleteMapping("/remove/{productId}")
    public RedirectView remove(
            @PathVariable String productId) {
        log.info("remove cart");
        String userId = "anh";
        cartService.removeItemFromCart(userId, productId);
        return new RedirectView("/api/v1/cart");
    }

    @GetMapping("/increase")
    public RedirectView increase(
            @RequestParam String productId) {
        log.info("increase cart");
        String userId = "anh";
        cartService.increaseItemQuantity(userId, productId);
        return new RedirectView("/api/v1/cart");
    }

    @GetMapping("/decrease")
    public RedirectView decrease(
            @RequestParam String productId) {
        log.info("decrease cart");
        String userId = "anh";
        cartService.decreaseItemQuantity(userId, productId);
        return new RedirectView("/api/v1/cart");
    }

}
