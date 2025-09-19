package com.ecommerce_gamer_api.dto.order;

import com.ecommerce_gamer_api.domain.order.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record OrderDetailResponseDTO(
        Long orderId,
        String status,
        BigDecimal totalPrice,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<OrderItemResponseDTO> items
) {
    public OrderDetailResponseDTO(Order order) {
        this(
                order.getId(),
                order.getStatus().name(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getItems().stream().map(OrderItemResponseDTO::new).collect(Collectors.toList())
        );
    }
}