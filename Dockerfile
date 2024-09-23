# Use an official OpenJDK runtime as a parent image
FROM maven:3.8.5-eclipse-temurin-17 AS build

# Set the working directory
WORKDIR /app

# Copy the jar file from the target directory
COPY target/spring-skeleton-1.0-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]