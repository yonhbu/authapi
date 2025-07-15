# AuthAPI - DummyJSON Login Logger

Este proyecto es una API REST construida con Java + Spring Boot que permite a usuarios autenticarse contra la API externa [DummyJSON](https://dummyjson.com) y registra cada autenticación válida en una base de datos PostgreSQL, incluyendo los datos del usuario autenticado y las cookies de sesión utilizadas.

---

## Instrucciones de ejecución

1. Clona el proyecto:
   ```bash
   git clone https://github.com/yonhbu/authapi.git
   cd authapi
   ```

2. Asegúrate de tener PostgreSQL ejecutándose localmente en el puerto `5432`, y crea la base de datos:
   ```sql
   CREATE DATABASE authdb;
   ```

3. Configura la conexión en `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/authdb
   spring.datasource.username=postgres
   spring.datasource.password=postgres
   ```

4. Ejecuta la aplicación con Maven:
   ```bash
   mvn clean spring-boot:run
   ```

   La API estará disponible en:  
   ```
   http://localhost:8080
   ```

---

## Usuario y contraseña de prueba (DummyJSON)

Puedes usar este usuario de prueba para autenticarte:

- **Usuario:** `emilys`
- **Contraseña:** `emilyspass`

Más ejemplos disponibles en: https://dummyjson.com/docs/auth

---

## Ejemplo `curl` para probar login

```bash
curl -X POST http://localhost:8080/api/login \
  -H "Content-Type: application/json" \
  -d '{
        "username": "emilys",
        "password": "emilyspass"
      }'
```

Esto realiza:

- Login en DummyJSON
- Llamada al endpoint `/auth/me` usando cookies
- Registro del evento en PostgreSQL
- Retorno de los datos básicos del usuario autenticado

---

## ¿Cómo se guarda el registro de login?

Por cada autenticación válida, se almacena un registro en la tabla `login_log`, con la siguiente información:

| Campo         | Descripción                                       |
|---------------|----------------------------------------------------|
| `id`          | ID autoincremental                                 |
| `username`    | Nombre del usuario usado en el login               |
| `login_time`  | Fecha y hora exacta del intento                    |
| `access_token`| Token JWT recibido de DummyJSON                    |
| `refresh_token` | Token de refresco (si está disponible)          |
| `cookies`     | Cookie enviada al endpoint `/auth/me`              |

La tabla se genera automáticamente al iniciar el proyecto gracias a Spring Data JPA con `ddl-auto=update`.

---

## Tecnologías usadas

- Java 21
- Spring Boot 3.5.3
- Spring Web
- Spring Data JPA
- PostgreSQL
- Feign Client
- Lombok
- DevTools

---

## Autor

Juan Vargas – [github.com/yonhbu](https://github.com/yonhbu)
