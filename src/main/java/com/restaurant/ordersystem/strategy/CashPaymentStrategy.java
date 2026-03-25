package com.restaurant.ordersystem.strategy;

import org.springframework.stereotype.Component;

@Component
public class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public String processPayment(Double amount) {
        return "Pago en efectivo procesado por $" + amount;
    }
}