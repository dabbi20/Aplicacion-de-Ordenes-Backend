package com.restaurant.ordersystem.service;

import com.restaurant.ordersystem.dto.CreateOrderRequest;
import com.restaurant.ordersystem.dto.OrderItemRequest;
import com.restaurant.ordersystem.entity.Order;
import com.restaurant.ordersystem.entity.OrderItem;
import com.restaurant.ordersystem.entity.Product;
import com.restaurant.ordersystem.entity.User;
import com.restaurant.ordersystem.enums.OrderStatus;
import com.restaurant.ordersystem.repository.OrderItemRepository;
import com.restaurant.ordersystem.repository.OrderRepository;
import com.restaurant.ordersystem.repository.ProductRepository;
import com.restaurant.ordersystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        ProductRepository productRepository,
                        UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Order createOrder(CreateOrderRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        order.setPaymentType(request.getPaymentType());
        order.setCreatedAt(LocalDateTime.now());

        double subtotal = 0.0;

        Order savedOrder = orderRepository.save(order);

        for (OrderItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setUnitPrice(product.getPrice());

            double itemSubtotal = product.getPrice() * itemRequest.getQuantity();
            orderItem.setSubtotal(itemSubtotal);

            subtotal += itemSubtotal;

            orderItemRepository.save(orderItem);
        }

        double tax = subtotal * 0.19;
        double total = subtotal + tax;

        savedOrder.setSubtotal(subtotal);
        savedOrder.setTax(tax);
        savedOrder.setTotal(total);

        return orderRepository.save(savedOrder);
    }
}