package com.ecommerce_gamer_api.controller;

import com.ecommerce_gamer_api.domain.User;
import com.ecommerce_gamer_api.dto.response.ErrorResponseDTO;
import com.ecommerce_gamer_api.dto.auth.LoginRequestDTO;
import com.ecommerce_gamer_api.dto.auth.RegisterRequestDTO;
import com.ecommerce_gamer_api.dto.auth.AuthResponseDTO;
import com.ecommerce_gamer_api.infra.security.TokenService;
import com.ecommerce_gamer_api.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequestDTO body) {
        Optional<User> userRequest = this.repository.findByEmail(body.email());

        if (userRequest.isPresent() && passwordEncoder.matches(body.password(), userRequest.get().getPassword())) {
            User user = userRequest.get();
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new AuthResponseDTO(user.getName(), token));
        }

        ErrorResponseDTO errorDTO = new ErrorResponseDTO("Usuário ou senha inválidos.", 401, Instant.now());

        return ResponseEntity.status(401).body(errorDTO);
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequestDTO body) {
        if (this.repository.findByEmail(body.email()).isPresent()) {
            ErrorResponseDTO errorDTO = new ErrorResponseDTO("Este email já está cadastrado.", 409, Instant.now());
            return ResponseEntity.status(409).body(errorDTO);
        }

        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(body.password()));
        newUser.setEmail(body.email());
        newUser.setName(body.name());
        this.repository.save(newUser);

        String token = tokenService.generateToken(newUser);
        return ResponseEntity.ok(new AuthResponseDTO(newUser.getName(), token));
    }
}
