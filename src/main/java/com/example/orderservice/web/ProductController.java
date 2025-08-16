package com.example.orderservice.web;

import com.example.orderservice.domain.Product;
import com.example.orderservice.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products")
public class ProductController {
  private final ProductService service;
  public ProductController(ProductService service) { this.service = service; }

  @GetMapping
  public List<Product> list() { return service.list(); }

  @GetMapping("/{id}")
  public Product get(@PathVariable("id") Long id) { return service.get(id); }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Product create(@RequestBody Product p) { return service.create(p); }
}
