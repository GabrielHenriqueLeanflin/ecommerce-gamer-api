package com.ecommerce_gamer_api.repository;

import com.ecommerce_gamer_api.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {}

