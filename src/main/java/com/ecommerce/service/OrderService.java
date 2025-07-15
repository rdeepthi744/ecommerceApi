package com.ecommerce.service;

import com.ecommerce.dto.OrderResponseDTO;
import com.ecommerce.entity.User;

import java.util.List;

public interface OrderService {
    List<OrderResponseDTO> getOrdersForUser(User user);
    List<OrderResponseDTO> getAllOrders();  // For Admin
}
