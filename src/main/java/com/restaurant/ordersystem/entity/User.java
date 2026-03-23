package com.restaurant.ordersystem.entity;

import com.restaurant.ordersystem.enums.Role;
import jakarta.persistence.*;

@Entity // Indica que esta clase es una entidad, es decir, una tabla en la base de datos
@Table(name = "users") // Define el nombre de la tabla en la BD
public class User {// Clase pública que representa la tabla users

    @Id// Define la clave primaria (ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Indica que el ID se genera automáticamente en la base de datos (autoincremental en PostgreSQL)
    private Long id;// Atributo privado, solo se accede mediante getters y setters, representa el identificador

    private String name;// Atributo privado de tipo String que representa el nombre del usuario

    private String email;// Atributo privado de tipo String que representa el correo del usuario

    private String password;// Atributo privado de tipo String que representa la contraseña del usuario

    @Enumerated(EnumType.STRING)// Indica que el enum se guardará como texto (ej: "ADMIN") y no como número
    private Role role;// Atributo que usa el enum Role (ADMIN o CLIENT)

    // constructor vacío (JPA lo necesita)
    public User() {// Constructor vacío requerido por JPA para poder crear objetos automáticamente
    }

    // getters y setters (luego podemos usar Lombok)
    public Long getId() { // Método público para obtener el ID
        return id;
    }

    public String getName() {
        return name; // Método público para obtener el nombre
    }

    public void setName(String name) {
        this.name = name;
    } // Método público para modificar el nombre

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
