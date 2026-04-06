package com.restaurant.ordersystem.service;

import com.restaurant.ordersystem.dto.AuthResponse;
import com.restaurant.ordersystem.dto.LoginRequest;
import com.restaurant.ordersystem.dto.RegisterRequest;
import com.restaurant.ordersystem.entity.User;
import com.restaurant.ordersystem.enums.Role;
import com.restaurant.ordersystem.repository.UserRepository;
import com.restaurant.ordersystem.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Registro público: siempre CLIENT
        user.setRole(Role.CLIENT);

        userRepository.save(user);

        AuthResponse response = new AuthResponse();
        response.setMessage("Usuario registrado correctamente");

        return response;
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generateToken(user);

        AuthResponse response = new AuthResponse();
        response.setMessage("Login exitoso");
        response.setToken(token);

        return response;
    }
}