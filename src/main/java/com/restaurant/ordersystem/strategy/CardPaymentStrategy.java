package com.restaurant.ordersystem.strategy;

import org.springframework.stereotype.Component;

@Component
public class CardPaymentStrategy implements PaymentStrategy {

    @Override
    public String processPayment(Double amount) {
        return "Pago con tarjeta procesado por $" + amount;
    }
}
