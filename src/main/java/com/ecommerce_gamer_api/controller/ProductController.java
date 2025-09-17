package com.ecommerce_gamer_api.controller;

import com.ecommerce_gamer_api.domain.user.Product;
import com.ecommerce_gamer_api.dto.product.ProductResponseDTO;
import com.ecommerce_gamer_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository repository;

    @GetMapping("/get-all")
    public ResponseEntity<ProductResponseDTO> getAllProducts(
            Pageable pageable,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String name
    ) {
        Page<Product> productPage = this.repository.findWithFilters(category, maxPrice, name, pageable);

        ProductResponseDTO response = new ProductResponseDTO(
                productPage.getContent(),
                productPage.getTotalElements()
        );

        return ResponseEntity.ok(response);
    }
}