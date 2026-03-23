FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy full project
COPY . .

# Build inside container
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Run app
CMD ["java", "-jar", "target/jobtracker-0.0.1-SNAPSHOT.jar"]