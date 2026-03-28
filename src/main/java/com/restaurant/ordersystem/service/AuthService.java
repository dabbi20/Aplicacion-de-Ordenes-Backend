package com.restaurant.ordersystem.service;

import com.restaurant.ordersystem.dto.AuthResponse;
import com.restaurant.ordersystem.dto.LoginRequest;
import com.restaurant.ordersystem.dto.RegisterRequest;
import com.restaurant.ordersystem.entity.User;
import com.restaurant.ordersystem.enums.Role;
import com.restaurant.ordersystem.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.restaurant.ordersystem.security.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    // REGISTRO
    public AuthResponse register(RegisterRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // luego encriptamos
        user.setRole(Role.CLIENT);

        userRepository.save(user);

        AuthResponse response = new AuthResponse();
        response.setMessage("Usuario registrado correctamente");

        return response;
    }

    // LOGIN
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generateToken(user.getEmail());

        AuthResponse response = new AuthResponse();
        response.setMessage("Login exitoso");
        response.setToken(token);

        return response;
    }
}