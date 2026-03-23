package com.restaurant.ordersystem.entity;//UBICAMOS LA CLASE DE PAQUETE ENTITY
import com.restaurant.ordersystem.enums.OrderStatus;//IMPORTAMOS LOS ENUMS ORDERSTATUS
import com.restaurant.ordersystem.enums.PaymentType;//IMPORTAMOS LOS ENUMS PAYMENTTYPE
import jakarta.persistence.*;//IMPORTAMOS LAS ANOTACIONS JPA QUE NOS PERMITEN COMUNICARNOS CON LA BASE DE DATOS
import java.time.LocalDateTime;//IMPORTAMOS UN TIPO DE DATO PARA MANEJAR FECHAS Y HORAS

@Entity// Indica que esta clase es una entidad, es decir, una tabla en la base de datos
@Table(name = "orders")// Define el nombre de la tabla en la BD
public class Order {// Clase pública que representa la tabla ORDERS
  @Id// Define la clave primaria (ID)
  @GeneratedValue(strategy = GenerationType.IDENTITY)// Indica que el ID se genera automáticamente en la base de datos (autoincremental en PostgreSQL)
  private Long id;// Atributo privado, solo se accede mediante getters y setters, representa el identificador
  @ManyToOne//MUCHOS A UNO (Muchas ordenes a un usuario)
  @JoinColumn(name = "user_id")//COLUMNA EN LA TABLA QUE APUNTA A LA TABLA USERS (nos facilita encontrar el usuario)
  private User user;//“Representa la relación con la entidad User (clave foránea)”
  @Enumerated(EnumType.STRING)// Indica que el enum se guardará como texto
  private OrderStatus status;//es un enum que define los valores válidos para el estado del pedido.
  private Double subtotal;
  private  Double tax;
  private Double total;
  @Enumerated(EnumType.STRING)
  @Column(name = "payment_type")//LE ASIGNAMOS NOMBRE A LA COLUMNA
  private PaymentType paymentType;
@Column(name = "created_at")//CREAMOS LA FECHA DE CREACION
private LocalDateTime createdAt;

public Order(){// Constructor vacío requerido por JPA para poder crear objetos automáticamente


}
//GETTERS Y SETTERS
    public Long getId(){
    return id;
    }
public User getUser(){
    return user;
}
public void setUser(User user){
    this.user = user;
}
public OrderStatus getStatus(){
    return  status;
}
public  void setStatus(OrderStatus status){
    this.status = status;
}
public Double getSubtotal(){
    return subtotal;
}
public void setSubtotal(Double subtotal){
    this.subtotal = subtotal;
}
public Double getTax(){
    return tax;
}
public void setTax(Double tax){
    this.tax = tax;
}
public  Double getTotal(){
    return total;
}
public void setTotal(Double total){
    this.total = total;
}
public PaymentType getPaymentType(){
    return paymentType;
}
public void setPaymentType(PaymentType paymentType){
    this.paymentType = paymentType;
}
public LocalDateTime getCreatedAt(){
    return  createdAt;
}
public void setCreatedAt(LocalDateTime createdAt){
    this.createdAt = createdAt;
}
}
