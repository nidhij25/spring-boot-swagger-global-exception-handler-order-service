package com.example.orderservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    @Email
    @NotEmpty
    private String customerEmail;

    @NotNull
    @NotEmpty
    private List<OrderItemRequest> items;
}
