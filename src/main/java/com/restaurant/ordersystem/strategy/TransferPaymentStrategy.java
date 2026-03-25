package com.restaurant.ordersystem.strategy;

import org.springframework.stereotype.Component;

@Component
public class TransferPaymentStrategy implements PaymentStrategy {

    @Override
    public String processPayment(Double amount) {
        return "Pago por transferencia procesado por $" + amount;
    }
}