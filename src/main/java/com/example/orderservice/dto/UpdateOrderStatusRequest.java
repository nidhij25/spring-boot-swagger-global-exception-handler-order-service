package com.example.orderservice.dto;

import com.example.orderservice.domain.OrderStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateOrderStatusRequest {
  @NotNull
  private OrderStatus status;

  public OrderStatus getStatus() { return status; }
  public void setStatus(OrderStatus status) { this.status = status; }
}
