package com.ecommerce_gamer_api.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank @Email(message = "O formato do email é inválido.")
        String email,

        @NotBlank
        String password
) {}