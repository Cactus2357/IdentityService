#FROM ubuntu:latest
#LABEL authors="hi"
#
#ENTRYPOINT ["top", "-b"]

# Stage 1: build
# Start with a Maven image that includes JDK 21
FROM maven:3.9.9-amazoncorretto-21 AS build
LABEL authors="hi"

# Copy source code and pom.xml file to /app folder
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Apply clean code format
RUN mvn spotless:apply

# Build source code with maven
RUN mvn package -DskipTests

#Stage 2: create image
# Start with Amazon Correto JDK 21
FROM amazoncorretto:21.0.5

# Set working folder to App and copy complied file from above step
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

# docker build -t identity-service:0.0.1 .
# docker network create identity-network
# docker network ls
# docker run --network identity-network --name identity-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:8.0.36-debian
# docker run --name identity-service --network identity-network -p 8080:8080 -e DBMS_CONNECTION=jdbc:mysql://mysql:3309/identity_service identity-service:0.0.1