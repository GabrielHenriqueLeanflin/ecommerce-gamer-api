package com.ecommerce_gamer_api.service;

import com.ecommerce_gamer_api.config.exception.ResourceNotFoundException;
import com.ecommerce_gamer_api.domain.Product;
import com.ecommerce_gamer_api.dto.product.ProductRequestDTO;
import com.ecommerce_gamer_api.dto.product.ProductResponseDTO;
import com.ecommerce_gamer_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Page<ProductResponseDTO> findAllProducts(String category, BigDecimal maxPrice, String name, Pageable pageable) {
        Page<Product> productPage = repository.findWithFilters(category, maxPrice, name, pageable);

        return productPage.map(ProductResponseDTO::new);
    }

    public ProductResponseDTO findProductById(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        return new ProductResponseDTO(product);
    }

    public ProductResponseDTO createProduct(ProductRequestDTO productDTO) {
        Product newProduct = new Product(productDTO);
        this.repository.save(newProduct);
        return new ProductResponseDTO(newProduct);
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productDTO) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        if (productDTO.name() != null) {
            product.setName(productDTO.name());
        }
        if (productDTO.description() != null) {
            product.setDescription(productDTO.description());
        }
        if (productDTO.price() != null) {
            product.setPrice(productDTO.price());
        }
        if (productDTO.category() != null) {
            product.setCategory(productDTO.category());
        }
        if (productDTO.brand() != null) {
            product.setBrand(productDTO.brand());
        }
        if (productDTO.stock() != null) {
            product.setStock(productDTO.stock());
        }

        repository.save(product);
        return new ProductResponseDTO(product);
    }

    public void deleteProduct(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }
        repository.deleteById(id);
    }
}