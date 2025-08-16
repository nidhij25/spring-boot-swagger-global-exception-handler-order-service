package com.example.orderservice.service;

import com.example.orderservice.domain.*;
import com.example.orderservice.dto.*;
import com.example.orderservice.repo.OrderRepository;
import com.example.orderservice.repo.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
  private final OrderRepository orders;
  private final ProductRepository products;

  public OrderService(OrderRepository orders, ProductRepository products) {
    this.orders = orders; this.products = products;
  }

  @Transactional
  public Order create(CreateOrderRequest req) {
    Order order = new Order();
    order.setCustomerName(req.getCustomerName());
    order.setCustomerEmail(req.getCustomerEmail());
    order.setShippingAddress(req.getShippingAddress());

    req.getItems().forEach(it -> {
      Product p = products.findById(it.getProductId())
          .orElseThrow(() -> new IllegalArgumentException("Product not found: " + it.getProductId()));
      OrderItem oi = new OrderItem();
      oi.setProductId(p.getId());
      oi.setProductName(p.getName());
      oi.setUnitPrice(p.getPrice());
      oi.setQuantity(it.getQuantity());
      oi.setSubtotal(BigDecimal.ZERO);
      order.addItem(oi);
    });
    order.recalcTotal();
    return orders.save(order);
  }

  public Order get(Long id) {
    return orders.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
  }
  public OrderResponse getOrderDetail(Long id) {
    return toResponse(orders.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found: " + id)));
  }


  public List<OrderResponse> list() {
    return orders.findAll().stream().map(this::toResponse).collect(Collectors.toList());
  }

  @Transactional
  public Order updateStatus(Long id, OrderStatus status) {
    Order o = get(id);
    o.setStatus(status);
    o.recalcTotal();
    return o;
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
