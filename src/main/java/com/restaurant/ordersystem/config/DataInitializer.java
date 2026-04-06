package com.restaurant.ordersystem.config;

import com.restaurant.ordersystem.entity.User;
import com.restaurant.ordersystem.enums.Role;
import com.restaurant.ordersystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String adminEmail = "superadmin@test.com";

            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                User admin = new User();
                admin.setName("Administrador");
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode("1234"));
                admin.setRole(Role.ADMIN);

                userRepository.save(admin);

                System.out.println("ADMIN creado correctamente: " + adminEmail);
            } else {
                System.out.println("El admin ya existe: " + adminEmail);
            }
        };
    }
}