package com.ecommerce_gamer_api.dto.product;

import com.ecommerce_gamer_api.domain.Product;

import java.math.BigDecimal;

public record ProductResponseDTO(
        long id,
        String name,
        BigDecimal price,
        String category,
        String brand,
        Integer stock
) {
    public ProductResponseDTO(Product product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getCategory(), product.getBrand(), product.getStock());
    }
}