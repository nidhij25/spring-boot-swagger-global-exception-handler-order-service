package com.example.orderservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.orderservice.domain.Product;
import com.example.orderservice.repo.ProductRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class OrderServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(OrderServiceApplication.class, args);
  }

  @Bean
  CommandLineRunner seed(ProductRepository products) {
    return args -> {
      if (products.count() == 0) {
        products.save(new Product(null, "SKU-1001", "Wireless Mouse", new BigDecimal("699.00")));
        products.save(new Product(null, "SKU-1002", "Mechanical Keyboard", new BigDecimal("3499.00")));
        products.save(new Product(null, "SKU-1003", "27\" Monitor", new BigDecimal("15499.00")));
      }
    };
  }
}
