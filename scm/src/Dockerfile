# ------------ 1. Build Stage ------------
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set work directory
WORKDIR scm/app

# Copy pom.xml and download dependencies first (cache layer)
COPY scm/pom.xml .
RUN mvn dependency:go-offline

# Copy the entire project
COPY scm/. .

# Build the application
RUN mvn clean package -DskipTests


# ------------ 2. Run Stage ------------
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR scm/app

# Copy JAR from build stage
COPY --from=build scm/app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
