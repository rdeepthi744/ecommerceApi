// CartService.java
package com.ecommerce.service;

import com.ecommerce.dto.AddToCartRequest;
import com.ecommerce.dto.CartItemDTO;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.User;

import java.util.List;

public interface CartService {
    void addToCart(AddToCartRequest request, User user);

    List<CartItemDTO> getCartItems(User user);

    void removeFromCart(Long productId, User user);

    void clearCart(User user);

	CartItem addToCart(Long userId, Long productId, int quantity);

	void removeCartItem(Long cartItemId);

	List<CartItem> getCartItemsByUserId(Long userId);
}
