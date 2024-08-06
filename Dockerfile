# Use the official Maven image to build and run the application
FROM maven:3.8.1-openjdk-11-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file and the source code to the container
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Expose the application port
EXPOSE 3000

# Run the application
CMD ["java", "-jar", "target/spring-boot-crud-0.0.1-SNAPSHOT.jar"]
