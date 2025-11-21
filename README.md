# ecommerce-tuapp

Proyecto Spring Boot (Maven) con JDK 17 — plantilla de e-commerce.

## Requisitos
- JDK 17
- Maven
- MySQL
- Redis (opcional)
- Visual Studio Code (recomendado: extensión Java + Spring Boot)

## Ejecutar
1. Configura `application.properties` con tu usuario/contraseña de MySQL.
2. Crear la base de datos `ecommerce_db` en MySQL.
3. Desde la raíz del proyecto:
   ```
   mvn spring-boot:run
   ```
4. La API arranca en `http://localhost:8080/api`
