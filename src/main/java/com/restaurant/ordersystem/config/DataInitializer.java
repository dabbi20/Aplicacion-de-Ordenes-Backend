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

            User admin = userRepository.findByEmail(adminEmail)
                    .orElse(null);

            if (admin == null) {
                // CREAR ADMIN
                admin = new User();
                admin.setName("Administrador");
                admin.setEmail(adminEmail);
                admin.setRole(Role.ADMIN);

                System.out.println("ADMIN creado correctamente: " + adminEmail);
            } else {
                System.out.println("ADMIN ya existe, actualizando contraseña...");
            }

            //  SIEMPRE ACTUALIZA LA CONTRASEÑA
            admin.setPassword(passwordEncoder.encode("123456"));

            userRepository.save(admin);
        };
    }
}