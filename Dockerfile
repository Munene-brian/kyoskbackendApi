FROM openjdk:17-jdk-slim

WORKDIR /app

RUN mvn clean package -DskipTests

COPY target/*.jar app.jar

EXPOSE 8089


CMD ["java", "-jar", "app.jar"]
