package com.ecommerce_gamer_api.repository;

import com.ecommerce_gamer_api.domain.order.Order;
import com.ecommerce_gamer_api.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByUser(User user, Pageable pageable);
}
