package com.restaurant.ordersystem.repository;
import com.restaurant.ordersystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product, Long> {

}
