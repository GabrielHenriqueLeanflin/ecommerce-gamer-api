package com.ecommerce_gamer_api.domain;

import com.ecommerce_gamer_api.dto.product.ProductRequestDTO; // Importe o DTO
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity(name = "product")
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private String category;

    private String brand;

    private Integer stock;

    public Product(ProductRequestDTO data) {
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
        this.category = data.category();
        this.brand = data.brand();
        this.stock = data.stock();
    }
}