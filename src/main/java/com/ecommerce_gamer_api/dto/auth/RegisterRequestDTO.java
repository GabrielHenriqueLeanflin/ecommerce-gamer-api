package com.ecommerce_gamer_api.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDTO(
        @NotBlank
        String name,

        @NotBlank
        @Email(message = "O formato do email é inválido.")
        String email,

        @NotBlank
        String password
) {}
