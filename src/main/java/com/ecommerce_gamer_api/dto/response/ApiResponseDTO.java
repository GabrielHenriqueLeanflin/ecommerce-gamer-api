package com.ecommerce_gamer_api.dto.response;

public record ApiResponseDTO<T>(
        boolean success,
        String message,
        T data
) {}