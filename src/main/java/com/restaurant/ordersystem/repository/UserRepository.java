package com.restaurant.ordersystem.repository;
import com.restaurant.ordersystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long>{

}
