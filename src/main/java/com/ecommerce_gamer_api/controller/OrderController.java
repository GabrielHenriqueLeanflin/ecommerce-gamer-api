package com.ecommerce_gamer_api.controller;

import com.ecommerce_gamer_api.dto.order.OrderRequestDTO;
import com.ecommerce_gamer_api.dto.response.ApiResponseDTO;
import com.ecommerce_gamer_api.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO<Object>> createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        orderService.createOrder(orderRequestDTO);
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(true, "Pedido criado com sucesso.", null);
        return ResponseEntity.status(201).body(response);
    }
}