package com.restaurant.ordersystem.observer;

import com.restaurant.ordersystem.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderEventManager {

    private final List<OrderObserver> observers;

    public OrderEventManager(List<OrderObserver> observers) {
        this.observers = observers;
    }

    public void notifyObservers(Order order) {
        for (OrderObserver observer : observers) {
            observer.update(order);
        }
    }
}