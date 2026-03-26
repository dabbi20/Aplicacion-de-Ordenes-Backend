package com.restaurant.ordersystem.builder;

import com.restaurant.ordersystem.entity.Order;
import com.restaurant.ordersystem.entity.User;
import com.restaurant.ordersystem.enums.OrderStatus;
import com.restaurant.ordersystem.enums.PaymentType;

import java.time.LocalDateTime;

public class OrderBuilder {

    private final Order order;

    public OrderBuilder() {
        this.order = new Order();
    }

    public OrderBuilder setUser(User user) {
        order.setUser(user);
        return this;
    }

    public OrderBuilder setStatus(OrderStatus status) {
        order.setStatus(status);
        return this;
    }

    public OrderBuilder setPaymentType(PaymentType paymentType) {
        order.setPaymentType(paymentType);
        return this;
    }

    public OrderBuilder setCreatedAt(LocalDateTime createdAt) {
        order.setCreatedAt(createdAt);
        return this;
    }

    public Order build() {
        return order;
    }
}