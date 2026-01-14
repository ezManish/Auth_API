# Spring Boot JWT Authentication & SSO API

This project is a centralized **Identity Provider (IdP)**. It uses **JWT (JSON Web Tokens)** to secure multiple applications.
It comes with **Global CORS** enabled, making it ready to serve as a Single Sign-On (SSO) backend for your frontend apps (React, Vue, etc.).

## Features
- **User Login** (`/api/login`) → Returns JWT
- **Protected Endpoints** (e.g., `/api/hello`) → Requires JWT
- **BCrypt** Password Encoding
- **Stateless Authentication** (No Sessions)
- **CORS Enabled** (Ready for external frontends)
- **Database Support**: H2 (In-memory) or MySQL/PostgreSQL (Cloud/Aiven)

---

## Prerequisites
Before you begin, ensure you have the following installed:
- **Java 17** or higher
- **Maven** (optional, wrapper provided)
- **Git**

---

## Project Structure
```
src/main/java/com/example/demo
├── config/
│   └── CorsConfig.java          # Global CORS settings (who can access this API)
├── exception/
│   ├── GlobalExceptionHandler.java # Returns nice JSON errors (401, 500)
│   └── InvalidCredentialsException.java
├── AppUser.java                 # User entity (Database table)
├── AuthController.java          # Login endpoints
├── AuthService.java             # Business logic for authentication
├── JwtUtil.java                 # Generates & validates Tokens
└── SecurityConfig.java          # Spring Security rules (Authorized vs Public)
```

---

## Setup & Run

### 1. Clone & Build
```bash
git clone https://github.com/ezManish/Auth_API.git
cd Auth_API
mvn clean spring-boot:run
```
The API will start on **http://localhost:8080** (default).

### 2. Database Configuration
By default, it runs on **H2 (In-memory)**. To connect to a real database (like **Aiven**), update `src/main/resources/application.properties`:

**For Aiven (MySQL)**:
```properties
spring.datasource.url=jdbc:mysql://YOUR-AIVEN-URL:PORT/defaultdb?ssl-mode=REQUIRED
spring.datasource.username=avnadmin
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

---

## API Endpoints

### 1. Login (Get Token)
- **URL**: `POST /api/login`
- **Body**:
  ```json
  {
    "username": "student1",
    "password": "pass123"
  }
  ```
- **Response** (200 OK): `eyJhbGciOiJIUzI...` (The Token)
- **Response** (401 Unauthorized): `Invalid username or password`

### 2. Access Protected Resource
- **URL**: `GET /api/hello`
- **Headers**:
  ```
  Authorization: Bearer <YOUR_JWT_TOKEN>
  ```
- **Response**: `Hello, you are authenticated with JWT!`

---

## Integration Guide (SSO)

You can use this API as the login backend for multiple other projects (Project A, Project B).

### How to Connect (Frontend/JS)
1.  **Login**: Call `POST /api/login` to get the token.
2.  **Save Token**: Store it in `localStorage`.
3.  **Use Token**: Send it in the header for every future request.

**Example (Fetch API)**:
```javascript
// A. LOGIN
const response = await fetch('http://localhost:8080/api/login', {
  method: 'POST',
  body: JSON.stringify({ username: 'user', password: 'password' }),
  headers: { 'Content-Type': 'application/json' }
});
const token = await response.text();
localStorage.setItem('jwt', token);

// B. ACCESS
await fetch('http://localhost:8080/api/hello', {
  headers: { 'Authorization': `Bearer ${token}` }
});
```

### SSO Configuration Checklist
If you have other backend services (Project A, Project B) that need to valid users:

| Setting | Auth API (Hub) | Project A / B (You) |
| :--- | :--- | :--- |
| **Database Credentials** | YES | NO |
| **JWT Secret Key** | YES (Create Token) | YES (Verify Token) |

**Important**: Copy the `jwt.secret` from this project's `application.properties` to your other projects so they can verify the tokens are valid.

---

## Troubleshooting

### 1. "Address already in use"
- **Cause**: Port 8080 is blocked.
- **Fix**: Open `application.properties` and add `server.port=8082`.

### 2. "Connection refused" to Database
- **Cause**: Wrong URL or Password in `application.properties`.
- **Fix**: Double-check your Aiven credentials. Ensure SSL is enabled (`?ssl-mode=REQUIRED`).

### 3. "Cross-Origin Request Blocked" (CORS)
- **Cause**: Frontend is on a different domain.
- **Fix**: This project allows `*` by default. If it fails, check `CorsConfig.java` to ensure your specific domain is allowed.

