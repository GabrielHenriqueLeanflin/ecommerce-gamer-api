package com.ecommerce_gamer_api.dto.response;

import java.time.Instant;

public record ErrorResponseDTO(String message, Integer status, Instant timestamp) {
}
