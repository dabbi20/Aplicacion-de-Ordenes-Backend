package com.restaurant.ordersystem.observer;

import com.restaurant.ordersystem.entity.Order;

public interface OrderObserver {
    void update(Order order);
}