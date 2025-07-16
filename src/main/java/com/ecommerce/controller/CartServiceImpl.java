package com.ecommerce.controller;

import com.ecommerce.dto.AddToCartRequest;
import com.ecommerce.dto.CartItemDTO;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public abstract class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void addToCart(AddToCartRequest request, User user) {
        Product product = productRepository.findById(request.getProductId()).orElseThrow();

        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product)
                .orElse(new CartItem());

        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(cartItem.getId() == null ? request.getQuantity() : cartItem.getQuantity() + request.getQuantity());
        cartItem.setPrice(product.getPrice() * cartItem.getQuantity());

        cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItemDTO> getCartItems(User user) {
        return cartItemRepository.findByUser(user)
                .stream()
                .map(cartItem -> new CartItemDTO(
                        cartItem.getProduct().getId(),
                        cartItem.getProduct().getName(),
                        cartItem.getQuantity(),
                        cartItem.getPrice()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void removeFromCart(Long productId, User user) {
        Product product = productRepository.findById(productId).orElseThrow();
        cartItemRepository.deleteByUserAndProduct(user, product);
    }

    @Override
    public void clearCart(User user) {
        cartItemRepository.deleteByUser(user);
    }

	@Override
	public CartItem addToCart(Long userId, Long productId, int quantity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeCartItem(Long cartItemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CartItem> getCartItemsByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
}


