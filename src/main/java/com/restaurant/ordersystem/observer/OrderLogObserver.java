package com.restaurant.ordersystem.observer;

import com.restaurant.ordersystem.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderLogObserver implements OrderObserver {

    @Override
    public void update(Order order) {
        System.out.println("LOG: El pedido " + order.getId() +
                " cambió a estado " + order.getStatus());
    }
}