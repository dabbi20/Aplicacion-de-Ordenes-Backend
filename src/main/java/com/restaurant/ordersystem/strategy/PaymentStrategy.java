package com.restaurant.ordersystem.strategy;

public interface PaymentStrategy {
    String processPayment(Double amount);
}