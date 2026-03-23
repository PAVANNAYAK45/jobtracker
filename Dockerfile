FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy full project
COPY . .

# Build inside container
RUN ./mvnw clean package -DskipTests

# Run app
CMD ["java", "-jar", "target/jobtracker-0.0.1-SNAPSHOT.jar"]