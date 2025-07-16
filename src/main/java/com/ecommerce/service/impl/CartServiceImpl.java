

//CartServiceImpl.java
package com.ecommerce.service.impl;

import com.ecommerce.dto.AddToCartRequest;
import com.ecommerce.dto.CartItemDTO;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

 @Autowired
 private CartItemRepository cartItemRepository;

 @Autowired
 private ProductRepository productRepository;

 @Override
 public void addToCart(AddToCartRequest request, User user) {
     Product product = productRepository.findById(request.getProductId())
             .orElseThrow(() -> new RuntimeException("Product not found"));

     CartItem existingItem = cartItemRepository.findByUserAndProduct(user, product);

     if (existingItem != null) {
         existingItem.setQuantity(existingItem.getQuantity() + request.getQuantity());
         existingItem.setPrice(product.getPrice() * existingItem.getQuantity());
         cartItemRepository.save(existingItem);
     } else {
         CartItem newItem = new CartItem();
         newItem.setUser(user);
         newItem.setProduct(product);
         newItem.setQuantity(request.getQuantity());
         newItem.setPrice(product.getPrice() * request.getQuantity());
         cartItemRepository.save(newItem);
     }
 }

 @Override
 public List<CartItemDTO> getCartItems(User user) {
     List<CartItem> items = cartItemRepository.findByUser(user);

     return items.stream().map(item -> {
         CartItemDTO dto = new CartItemDTO();
         dto.setProductId(item.getProduct().getId());
         dto.setProductName(item.getProduct().getName());
         dto.setQuantity(item.getQuantity());
         dto.setPrice(item.getPrice());
         return dto;
     }).collect(Collectors.toList());
 }

 @Override
 public void removeFromCart(Long productId, User user) {
     Product product = productRepository.findById(productId)
             .orElseThrow(() -> new RuntimeException("Product not found"));
     cartItemRepository.deleteByUserAndProduct(user, product);
 }

 @Override
 public void clearCart(User user) {
     cartItemRepository.deleteByUser(user);
 }
}
