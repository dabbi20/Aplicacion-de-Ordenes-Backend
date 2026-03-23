package com.restaurant.ordersystem.repository;
import com.restaurant.ordersystem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
