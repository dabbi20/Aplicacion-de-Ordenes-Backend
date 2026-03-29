#  Restaurant Order System

Sistema backend para la gestión de pedidos de un restaurante desarrollado con **Java + Spring Boot**, aplicando patrones de diseño y buenas prácticas de arquitectura.

---

##  Características principales

- Registro e inicio de sesión de usuarios
- Creación de órdenes con múltiples productos
- Cálculo automático de subtotal, impuestos y total
- Manejo de diferentes métodos de pago
- Autenticación con JWT
- Arquitectura en capas (Controller, Service, Repository)

---

##  Patrones de diseño implementados

###  Strategy
Se utiliza para manejar diferentes métodos de pago:
- CASH
- CARD
- TRANSFER

Permite agregar nuevos métodos sin modificar el código existente.

---

###  Observer
Permite reaccionar a cambios en el estado de una orden.

Ejemplo:
- Notificación cuando una orden cambia de estado

---

###  Builder
Se utiliza para construir objetos complejos como `Order` de forma clara y flexible.

---

##  Arquitectura

El proyecto sigue una arquitectura en capas:

- **Controller** → Manejo de endpoints
- **Service** → Lógica de negocio
- **Repository** → Acceso a datos
- **DTO** → Control de datos expuestos
- **Entity** → Modelado de base de datos

---

## ️ Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- PostgreSQL
- Maven

---

##  Autenticación

El sistema utiliza JWT para autenticación:

1. El usuario se registra
2. Hace login
3. Recibe un token
4. Usa ese token para acceder a endpoints protegidos

---

##  Endpoints principales

###  Auth

#### Registro
POST /api/auth/register
#### Login
POST /api/auth/login

---

###  Orders

#### Crear orden

POST /api/orders

---

##  Cómo ejecutar el proyecto

### 1. Clonar repositorio

git clone https://github.com/dabbi20/Aplicacion-de-Ordenes-Backend.git


### 2. Entrar al proyecto

cd restaurant-order-system


### 3. Ejecutar
./mvnw clean install

./mvnw spring-boot:run


---

##  Base de datos

Configurar PostgreSQL:

- DB: `restaurant_db`
- User: `postgres`
- Password: `admin123`





---

##  Autor

- Nombre: David Manuel Carrasco
- Proyecto académico
