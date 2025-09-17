package com.ecommerce_gamer_api.dto.product;

import com.ecommerce_gamer_api.domain.user.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private List<Product> result;
    private long count;
}