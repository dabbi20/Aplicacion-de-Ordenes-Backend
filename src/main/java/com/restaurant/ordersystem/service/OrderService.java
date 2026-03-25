package com.restaurant.ordersystem.service;

import com.restaurant.ordersystem.dto.CreateOrderRequest;
import com.restaurant.ordersystem.dto.OrderItemRequest;
import com.restaurant.ordersystem.dto.OrderItemResponse;
import com.restaurant.ordersystem.dto.OrderResponse;
import com.restaurant.ordersystem.entity.Order;
import com.restaurant.ordersystem.entity.OrderItem;
import com.restaurant.ordersystem.entity.Product;
import com.restaurant.ordersystem.entity.User;
import com.restaurant.ordersystem.enums.OrderStatus;
import com.restaurant.ordersystem.repository.OrderItemRepository;
import com.restaurant.ordersystem.repository.OrderRepository;
import com.restaurant.ordersystem.repository.ProductRepository;
import com.restaurant.ordersystem.repository.UserRepository;
import com.restaurant.ordersystem.enums.PaymentType;
import com.restaurant.ordersystem.strategy.CardPaymentStrategy;
import com.restaurant.ordersystem.strategy.CashPaymentStrategy;
import com.restaurant.ordersystem.strategy.PaymentStrategy;
import com.restaurant.ordersystem.strategy.TransferPaymentStrategy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CashPaymentStrategy cashPaymentStrategy;
    private final CardPaymentStrategy cardPaymentStrategy;
    private final TransferPaymentStrategy transferPaymentStrategy;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        ProductRepository productRepository,
                        UserRepository userRepository,
                        CashPaymentStrategy cashPaymentStrategy,
                        CardPaymentStrategy cardPaymentStrategy,
                        TransferPaymentStrategy transferPaymentStrategy) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cashPaymentStrategy = cashPaymentStrategy;
        this.cardPaymentStrategy = cardPaymentStrategy;
        this.transferPaymentStrategy = transferPaymentStrategy;
    }
    private PaymentStrategy getPaymentStrategy(PaymentType paymentType) {
        return switch (paymentType) {
            case CASH -> cashPaymentStrategy;
            case CARD -> cardPaymentStrategy;
            case TRANSFER -> transferPaymentStrategy;
        };
    }
    public OrderResponse createOrder(CreateOrderRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        order.setPaymentType(request.getPaymentType());
        order.setCreatedAt(LocalDateTime.now());

        double subtotal = 0.0;

        Order savedOrder = orderRepository.save(order);

        List<OrderItemResponse> itemResponses = new ArrayList<>();

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

            OrderItemResponse itemResponse = new OrderItemResponse();
            itemResponse.setProductId(product.getId());
            itemResponse.setProductName(product.getName());
            itemResponse.setQuantity(itemRequest.getQuantity());
            itemResponse.setUnitPrice(product.getPrice());
            itemResponse.setSubtotal(itemSubtotal);

            itemResponses.add(itemResponse);
        }

        double tax = subtotal * 0.19;
        double total = subtotal + tax;

        PaymentStrategy paymentStrategy = getPaymentStrategy(request.getPaymentType());
        String paymentMessage = paymentStrategy.processPayment(total);
        System.out.println(paymentMessage);

        savedOrder.setSubtotal(subtotal);
        savedOrder.setTax(tax);
        savedOrder.setTotal(total);

        Order finalOrder = orderRepository.save(savedOrder);

        OrderResponse response = new OrderResponse();
        response.setOrderId(finalOrder.getId());
        response.setUserId(user.getId());
        response.setUserName(user.getName());
        response.setStatus(finalOrder.getStatus());
        response.setSubtotal(subtotal);
        response.setTax(tax);
        response.setTotal(total);
        response.setPaymentType(finalOrder.getPaymentType());
        response.setCreatedAt(finalOrder.getCreatedAt());
        response.setItems(itemResponses);

        return response;
    }
}

