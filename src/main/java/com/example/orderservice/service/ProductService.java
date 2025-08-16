package com.example.orderservice.service;

import com.example.orderservice.domain.Product;
import com.example.orderservice.repo.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {
  private final ProductRepository repo;
  public ProductService(ProductRepository repo) { this.repo = repo; }

  public List<Product> list() { return repo.findAll(); }
  public Product get(Long id) { return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found: " + id)); }

  @Transactional
  public Product create(Product p) { return repo.save(p); }
}
