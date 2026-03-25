package com.restaurant.ordersystem.observer;

import com.restaurant.ordersystem.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class CustomerNotificationObserver implements OrderObserver {

    @Override
    public void update(Order order) {
        System.out.println("NOTIFICACIÓN: Se notificó al cliente del pedido "
                + order.getId() + " con estado " + order.getStatus());
    }
}