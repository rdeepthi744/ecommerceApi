package com.ecommerce.service.impl;

import com.ecommerce.dto.OrderResponseDTO;
import com.ecommerce.dto.OrderResponseDTO.ItemDTO;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.User;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // For Customer
    @Override
    public List<OrderResponseDTO> getOrdersForUser(User user) {
        List<Order> orders = orderRepository.findByUser(user);

        return orders.stream().map(this::mapToOrderResponseDTO).collect(Collectors.toList());
    }

    // For Admin
    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(this::mapToOrderResponseDTO).collect(Collectors.toList());
    }

    // Utility method to map Order -> OrderResponseDTO
    private OrderResponseDTO mapToOrderResponseDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setTotalAmount(order.getTotalAmount());

        List<ItemDTO> items = order.getItems() != null ? order.getItems().stream().map(item -> {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setProductId(item.getProduct().getId());
            itemDTO.setProductName(item.getProduct().getName());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setPrice(item.getPrice());
            return itemDTO;
        }).collect(Collectors.toList()) : List.of();

        dto.setItems(items);
        return dto;
    }
}
