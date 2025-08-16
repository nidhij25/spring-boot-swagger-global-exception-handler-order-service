package com.example.orderservice.dto;

import com.example.orderservice.domain.OrderStatus;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class OrderResponse {
  public Long id;
  public OrderStatus status;
  public String customerName;
  public String customerEmail;
  public String shippingAddress;
  public BigDecimal total;
  public OffsetDateTime createdAt;
  public OffsetDateTime updatedAt;
  public List<Item> items;

  public static class Item {
    public Long id;
    public Long productId;
    public String productName;
    public BigDecimal unitPrice;
    public Integer quantity;
    public BigDecimal subtotal;
  }
}
