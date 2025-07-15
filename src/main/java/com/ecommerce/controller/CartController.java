package com.ecommerce.controller;

import com.ecommerce.dto.AddToCartRequest;
import com.ecommerce.dto.CartItemDTO;
import com.ecommerce.entity.User;
import com.ecommerce.service.CartService;
import com.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addToCart(@Valid @RequestBody AddToCartRequest request,
                            @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        cartService.addToCart(user, request.getProductId(), request.getQuantity());
        return "Product added to cart!";
    }

    @GetMapping
    public List<CartItemDTO> getCartItems(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return cartService.getCartItems(user);
    }

    @DeleteMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        cartService.removeFromCart(user, productId);
        return "Product removed from cart.";
    }

    @DeleteMapping("/clear")
    public String clearCart(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        cartService.clearCart(user);
        return "Cart cleared.";
    }
}
