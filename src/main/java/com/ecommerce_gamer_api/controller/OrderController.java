package com.ecommerce_gamer_api.controller;

import com.ecommerce_gamer_api.config.exception.BusinessException;
import com.ecommerce_gamer_api.dto.order.OrderDetailResponseDTO;
import com.ecommerce_gamer_api.dto.order.OrderRequestDTO;
import com.ecommerce_gamer_api.dto.response.ApiResponseDTO;
import com.ecommerce_gamer_api.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponseDTO<OrderDetailResponseDTO>> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> statusUpdate) {

        String newStatus = statusUpdate.get("status");
        if (newStatus == null) {
            throw new BusinessException("O campo 'status' é obrigatório.");
        }

        OrderDetailResponseDTO updatedOrder = orderService.updateOrderStatus(id, newStatus);
        ApiResponseDTO<OrderDetailResponseDTO> response = new ApiResponseDTO<>(true, "Status do pedido atualizado com sucesso.", updatedOrder);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<OrderDetailResponseDTO>> getOrderById(@PathVariable Long id) {
        OrderDetailResponseDTO order = orderService.findOrderById(id);
        ApiResponseDTO<OrderDetailResponseDTO> response = new ApiResponseDTO<>(true, "Pedido encontrado.", order);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/my-orders")
    public ResponseEntity<ApiResponseDTO<Page<OrderDetailResponseDTO>>> getMyOrders(Pageable pageable) {
        Page<OrderDetailResponseDTO> orders = orderService.findOrdersByUser(pageable);
        ApiResponseDTO<Page<OrderDetailResponseDTO>> response = new ApiResponseDTO<>(true, "Pedidos do usuário listados com sucesso.", orders);
        return ResponseEntity.ok(response);
    }
}