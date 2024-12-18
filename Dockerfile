# Step 1: Use an official Java runtime as the base image
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy the JAR file into the container
# Use a wildcard to match any JAR file in the target directory
COPY target/*.jar app.jar

# Step 4: Expose the port your Spring Boot application runs on (default: 8080)
EXPOSE 8089

# Step 5: Specify the command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]
