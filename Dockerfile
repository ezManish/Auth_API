# ------------------------
# Stage 1: Build with Maven
# ------------------------
FROM maven:3.9.9-eclipse-temurin-17 AS builder

# Set working directory inside the container
WORKDIR /app

# Copy pom.xml and download dependencies first (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Package the app (skip tests for faster build)
RUN mvn clean package -DskipTests

# ------------------------
# Stage 2: Run with JRE only
# ------------------------
FROM eclipse-temurin:17-jre

# Set working directory
WORKDIR /app

# Copy only the built JAR from builder stage
COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose port (must match your Spring Boot `server.port` if changed)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
