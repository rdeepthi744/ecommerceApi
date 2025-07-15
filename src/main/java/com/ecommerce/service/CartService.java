package com.ecommerce.service;

import com.ecommerce.dto.CartItemDTO;
import com.ecommerce.entity.User;

import java.util.List;

public interface CartService {
    void addToCart(User user, Long productId, int quantity);
    List<CartItemDTO> getCartItems(User user);
    void removeFromCart(User user, Long productId);
    void clearCart(User user);
}
