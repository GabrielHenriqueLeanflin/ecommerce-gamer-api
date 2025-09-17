package com.ecommerce_gamer_api.config;

import com.ecommerce_gamer_api.domain.user.Product;
import com.ecommerce_gamer_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            System.out.println(">>> Adicionando o banco de dados com produtos iniciais...");

            List<Product> products = Arrays.asList(
                    new Product(null, "PC Gamer Alpha", "PC com RTX 4090, i9-13900K, 32GB RAM", new BigDecimal("18500.00"), "Computador"),
                    new Product(null, "PC Gamer Beta", "PC com RTX 4070, Ryzen 7 7800X3D, 16GB RAM", new BigDecimal("12300.50"), "Computador"),
                    new Product(null, "Notebook Gamer Predator", "Notebook com RTX 4060, i7-13700H, 16GB RAM", new BigDecimal("9800.00"), "Notebook"),
                    new Product(null, "Notebook Gamer Dell G15", "Notebook com RTX 3050, i5-12500H, 8GB RAM", new BigDecimal("5600.99"), "Notebook"),
                    new Product(null, "Mouse Logitech G Pro X Superlight", "Mouse sem fio ultraleve para gamers", new BigDecimal("650.00"), "Periférico"),
                    new Product(null, "Mouse Razer DeathAdder V3", "Mouse ergonômico com fio para FPS", new BigDecimal("450.00"), "Periférico"),
                    new Product(null, "Teclado Mecânico Redragon Kumara", "Teclado TKL com switchs Outemu Blue", new BigDecimal("280.00"), "Periférico"),
                    new Product(null, "Headset HyperX Cloud Alpha", "Headset com fio e som surround 7.1", new BigDecimal("550.00"), "Periférico"),
                    new Product(null, "Monitor Gamer Alienware 27\" OLED", "Monitor QHD 240Hz com painel OLED", new BigDecimal("6500.00"), "Monitor"),
                    new Product(null, "Monitor Gamer LG UltraGear 24\"", "Monitor Full HD 144Hz com painel IPS", new BigDecimal("1200.00"), "Monitor"),
                    new Product(null, "Cadeira Gamer DT3 Sports", "Cadeira ergonômica com ajuste de altura", new BigDecimal("1100.00"), "Cadeira"),
                    new Product(null, "Webcam Logitech C920", "Webcam Full HD para streaming", new BigDecimal("380.00"), "Periférico"),
                    new Product(null, "Microfone HyperX QuadCast S", "Microfone USB com iluminação RGB", new BigDecimal("950.00"), "Periférico"),
                    new Product(null, "PC Gamer Custo-Benefício", "PC com RX 6600, Ryzen 5 5600, 16GB RAM", new BigDecimal("4500.00"), "Computador"),
                    new Product(null, "Notebook Avell Storm", "Notebook com RTX 4080, i9-13900HX", new BigDecimal("19999.00"), "Notebook"),
                    new Product(null, "Mousepad Gamer HyperX Fury S", "Mousepad de tecido grande (900x420mm)", new BigDecimal("180.00"), "Periférico")
            );

            productRepository.saveAll(products);
            System.out.println(">>> " + products.size() + " produtos inseridos com sucesso!");
        } else {
            System.out.println(">>> O banco de dados de produtos já está adicionado.");
        }
    }
}