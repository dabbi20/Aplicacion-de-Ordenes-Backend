package com.restaurant.ordersystem.repository;
import com.restaurant.ordersystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<Order, Long >{
}
