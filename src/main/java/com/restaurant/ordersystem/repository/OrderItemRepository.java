package com.restaurant.ordersystem.repository;//Indica que la interface esta en el paquete repository
import com.restaurant.ordersystem.entity.OrderItem;//Importamos la entidad OrderItem con la que trabajaremos
import org.springframework.data.jpa.repository.JpaRepository;//Importamos JPARepository este trae los metodos listos para crear el CRUD
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {//Quiero un repositorio que maneje la entidad User y cuyo ID es de tipo Long

}
