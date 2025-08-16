package com.example.orderservice.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, unique = true)
  private String sku;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false, precision = 19, scale = 2)
  private BigDecimal price;

  public Product() {}
  public Product(Long id, String sku, String name, BigDecimal price) {
    this.id = id; this.sku = sku; this.name = name; this.price = price;
  }
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getSku() { return sku; }
  public void setSku(String sku) { this.sku = sku; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public BigDecimal getPrice() { return price; }
  public void setPrice(BigDecimal price) { this.price = price; }
}
