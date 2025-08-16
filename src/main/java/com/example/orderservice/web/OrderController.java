package com.example.orderservice.web;

import com.example.orderservice.domain.Order;
import com.example.orderservice.domain.OrderStatus;
import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.dto.OrderResponse;
import com.example.orderservice.dto.UpdateOrderStatusRequest;
import com.example.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Orders")
public class OrderController {
  private final OrderService service;
  public OrderController(OrderService service) { this.service = service; }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new order from product IDs and quantities")
  public OrderResponse create(@Valid @RequestBody CreateOrderRequest req) {
    return toResponse(service.create(req));
  }

  @GetMapping("/{id}")
  public OrderResponse get(@PathVariable("id") Long id) { return service.getOrderDetail(id); }

  @GetMapping
  public List<OrderResponse> list() {
    return service.list();
  }

  @PatchMapping("/{id}/status")
  public OrderResponse updateStatus(@PathVariable("id") Long id, @Valid @RequestBody UpdateOrderStatusRequest req) {
    Order updated = service.updateStatus(id, req.getStatus());
    return toResponse(updated);
  }
  private OrderResponse toResponse(Order o) {
    OrderResponse r = new OrderResponse();
    r.id = o.getId();
    r.status = o.getStatus();
    r.customerName = o.getCustomerName();
    r.customerEmail = o.getCustomerEmail();
    r.shippingAddress = o.getShippingAddress();
    r.total = o.getTotal();
    r.createdAt = o.getCreatedAt();
    r.updatedAt = o.getUpdatedAt();
    r.items = o.getItems().stream().map(oi -> {
      OrderResponse.Item item = new OrderResponse.Item();
      item.id = oi.getId();
      item.productId = oi.getProductId();
      item.productName = oi.getProductName();
      item.unitPrice = oi.getUnitPrice();
      item.quantity = oi.getQuantity();
      item.subtotal = oi.getSubtotal();
      return item;
    }).collect(Collectors.toList());
    return r;
  }

}
