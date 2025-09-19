package com.ecommerce_gamer_api.repository;

import com.ecommerce_gamer_api.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}

