package com.ecommerce_gamer_api.controller;

import com.ecommerce_gamer_api.dto.product.ProductRequestDTO;
import com.ecommerce_gamer_api.dto.product.ProductResponseDTO;
import com.ecommerce_gamer_api.dto.response.ApiResponseDTO;
import com.ecommerce_gamer_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    /** Services */
    private final ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(
            Pageable pageable,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String name
    ) {
        Page<ProductResponseDTO> productPage = this.service.findAllProducts(category, minPrice, maxPrice, name, pageable);
        return ResponseEntity.ok(productPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity <ApiResponseDTO<ProductResponseDTO>> getProductById(@PathVariable Long id) {
        ProductResponseDTO product = this.service.findProductById(id);
        ApiResponseDTO<ProductResponseDTO> response = new ApiResponseDTO<>(true, "Produto encontrado com sucesso.", product);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<ProductResponseDTO>> createProduct(
            @Valid @RequestBody ProductRequestDTO productDTO,
            UriComponentsBuilder uriBuilder) {
        ProductResponseDTO newProduct = this.service.createProduct(productDTO);

        ApiResponseDTO<ProductResponseDTO> response = new ApiResponseDTO<>(true, "Produto criado com sucesso.", newProduct);

        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(newProduct.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<ProductResponseDTO>> updateProduct(@Valid @PathVariable Long id, @RequestBody ProductRequestDTO productDTO) {
        ProductResponseDTO updatedProduct = this.service.updateProduct(id, productDTO);

        ApiResponseDTO<ProductResponseDTO> response = new ApiResponseDTO<>(true, "Produto atualizado com sucesso.", updatedProduct);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Object>> deleteProduct(@PathVariable Long id) {
        this.service.deleteProduct(id);

        ApiResponseDTO<Object> response = new ApiResponseDTO<>(true, "Produto com ID " + id + " removido com sucesso.", null);

        return ResponseEntity.ok(response);
    }
}