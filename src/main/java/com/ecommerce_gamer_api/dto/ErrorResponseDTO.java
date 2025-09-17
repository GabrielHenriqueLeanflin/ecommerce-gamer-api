package com.ecommerce_gamer_api.dto;

import java.time.Instant;

public record ErrorResponseDTO(String message, Integer status, Instant timestamp) {
}
