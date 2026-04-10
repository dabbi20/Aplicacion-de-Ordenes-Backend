package com.restaurant.ordersystem.controller;

import com.restaurant.ordersystem.dto.CreateOrderRequest;
import com.restaurant.ordersystem.dto.OrderResponse;
import com.restaurant.ordersystem.enums.OrderStatus;
import com.restaurant.ordersystem.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/my")
    public ResponseEntity<List<OrderResponse>> getMyOrders(Authentication authentication) {
        System.out.println("ENTRO A /api/orders/my");
        System.out.println("AUTH: " + authentication);

        String username = authentication.getName();
        System.out.println("USERNAME: " + username);

        return ResponseEntity.ok(orderService.getOrdersByUsername(username));
    }



    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        String statusValue = body.get("status");
        OrderStatus status = OrderStatus.valueOf(statusValue);
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }
}