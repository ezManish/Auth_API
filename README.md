# Spring Boot JWT Authentication API

This project is a **Spring Boot REST API** that demonstrates **secure user authentication and authorization** using **JWT (JSON Web Tokens)**.  
It provides **login and registration functionality**, protects endpoints with JWT, and stores passwords securely with **BCrypt**.

---

##  Features
- User Registration (`/api/register`)  
- User Login (`/api/login`) → returns JWT token  
- JWT-based Authentication & Authorization  
- Protected Endpoints (e.g., `/api/profile`)  
- Passwords stored securely with **BCrypt**  
- In-memory **H2 Database** 
- Configurable with `application.properties`

---

## Tech Stack
- **Java 17**
- **Spring Boot 3**
- **Spring Security**
- **Spring Data JPA**
- **JWT (JSON Web Tokens)**
- **H2 Database** (default)

---

## Setup & Run

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/jwt-auth-api.git
cd jwt-auth-api
```

## 2. Build & Run
Using Maven:
```bash
mvn spring-boot:run
```
Or using IntelliJ / VSCode: Run DemoApplication.java

The API will be available at:
```bash
http://localhost:8082
```
## Endpoints
- Register User:
```bash
POST /api/register
Content-Type: application/json
```
Body:
```bash
{
  "username": "student1",
  "password": "pass123"
}
```
- Login (Generate Token)
```bash
POST /api/login
Content-Type: application/json
```
Body:
```bash
{
  "username": "student1",
  "password": "pass123"
}
```
- Access Protected Endpoint
```bash
GET /api/profile
Authorization: Bearer <JWT_TOKEN>
```

## Screenshots 

- IntelliJ IDEA
  ---
<img width="1919" height="1019" alt="Screenshot 2025-09-25 162900" src="https://github.com/user-attachments/assets/d5c2bc1d-11fa-4a0f-9b19-106fa32b0b74" />


- Postman
  ---
<img width="1919" height="1018" alt="Screenshot 2025-09-25 162931" src="https://github.com/user-attachments/assets/58aa6d50-e742-42da-a74d-d9cccf8c88c7" />
