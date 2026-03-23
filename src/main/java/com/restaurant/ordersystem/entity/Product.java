package com.restaurant.ordersystem.entity;
import jakarta.persistence.*;

@Entity// Indica que esta clase es una entidad, es decir, una tabla en la base de datos
@Table(name = "products")// Define el nombre de la tabla en la BD
public class Product {// Clase pública que representa la tabla products
    @Id// Define la clave primaria (ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Indica que el ID se genera automáticamente en la base de datos (autoincremental en PostgreSQL)

    private  Long id;// Atributo privado, solo se accede mediante getters y setters, representa el identificador
    private  String name;// Atributo privado de tipo String que representa el nombre del producto
    private  String description;// Atributo privado de tipo String que representa la descripcion del producto
    private  Double price;// Atributo privado de tipo Double que representa el precio del producto
    private  Boolean available;// Atributo privado de tipo Boolean que representa el available del producto

    public  Product(){
    }// Constructor vacío requerido por JPA para poder crear objetos automáticamente

    // getters y setters (luego podemos usar Lombok)
    public Long getId() {// Método público para obtener el ID
        return id;
    }
    public String getName(){
        return name;// Método público para obtener el nombre
    }
    public void  setName(String name){
        this.name = name;// Método público para modificar el nombre
    }

    public  String getDescription(){
        return  description;
    }
    public  void setDescription(String description){
        this.description = description;
    }

    public  Double getPrice(){
        return price;
    }
    public  void  setPrice(Double price){
        this.price = price;
    }
    public Boolean getAvailable(){
        return available;
    }
    public  void  setAvailable(Boolean available){
        this.available = available;
    }
}
