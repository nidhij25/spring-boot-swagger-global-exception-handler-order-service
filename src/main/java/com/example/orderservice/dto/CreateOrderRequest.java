package com.example.orderservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class CreateOrderRequest {
  @NotBlank private String customerName;
  @NotBlank @Email private String customerEmail;
  @NotBlank private String shippingAddress;
  @NotEmpty private List<OrderItemRequest> items;

  public String getCustomerName() { return customerName; }
  public void setCustomerName(String customerName) { this.customerName = customerName; }
  public String getCustomerEmail() { return customerEmail; }
  public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
  public String getShippingAddress() { return shippingAddress; }
  public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
  public List<OrderItemRequest> getItems() { return items; }
  public void setItems(List<OrderItemRequest> items) { this.items = items; }
}
