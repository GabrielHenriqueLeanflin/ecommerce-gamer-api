package com.ecommerce_gamer_api.service;

import com.ecommerce_gamer_api.config.exception.BusinessException;
import com.ecommerce_gamer_api.config.exception.ResourceNotFoundException;
import com.ecommerce_gamer_api.domain.Order;
import com.ecommerce_gamer_api.domain.OrderItem;
import com.ecommerce_gamer_api.domain.Product;
import com.ecommerce_gamer_api.domain.User;
import com.ecommerce_gamer_api.dto.order.OrderRequestDTO;
import com.ecommerce_gamer_api.repository.OrderRepository;
import com.ecommerce_gamer_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void createOrder(OrderRequestDTO orderDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Order order = new Order();
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PENDING");

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (var itemDTO : orderDTO.items()) {
            Product product = productRepository.findById(itemDTO.product())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto com ID " + itemDTO.product() + " não encontrado."));

            if (product.getStock() < itemDTO.quantity()) {
                throw new BusinessException("Estoque insuficiente para o produto: " + product.getName());
            }
            product.setStock(product.getStock() - itemDTO.quantity());

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.quantity());
            orderItem.setPrice(product.getPrice());
            orderItem.setOrder(order);
            orderItems.add(orderItem);

            totalPrice = totalPrice.add(product.getPrice().multiply(new BigDecimal(itemDTO.quantity())));
        }

        order.setItems(orderItems);
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);
    }
}