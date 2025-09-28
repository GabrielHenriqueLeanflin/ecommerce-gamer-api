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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequestDTO body) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(body.email(), body.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new AuthResponseDTO(user.getName(), token));
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
