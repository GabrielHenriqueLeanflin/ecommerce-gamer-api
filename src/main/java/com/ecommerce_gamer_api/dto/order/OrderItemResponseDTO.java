package com.ecommerce_gamer_api.dto.order;

import com.ecommerce_gamer_api.domain.order.OrderItem;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        String productName,
        Integer quantity,
        BigDecimal price
) {
    public OrderItemResponseDTO(OrderItem item) {
        this(item.getProduct().getName(), item.getQuantity(), item.getPrice());
    }
}