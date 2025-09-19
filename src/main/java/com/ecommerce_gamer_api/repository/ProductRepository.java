package com.ecommerce_gamer_api.repository;

import com.ecommerce_gamer_api.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM product p WHERE " +
            "(:category IS NULL OR p.category = :category) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
            "(:name IS NULL OR lower(p.name) LIKE lower(concat('%', :name, '%')))")
    Page<Product> findWithFilters(
            @Param("category") String category,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("name") String name,
            Pageable pageable);
}