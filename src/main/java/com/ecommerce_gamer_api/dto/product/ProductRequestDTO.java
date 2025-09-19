package com.ecommerce_gamer_api.dto.product;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductRequestDTO(
        @NotBlank
        String name,

        String description,

        @NotNull
        @Positive
        BigDecimal price,

        @NotNull
        @PositiveOrZero
        Integer stock,

        @NotBlank
        String category,

        @NotBlank
        String brand
) {}