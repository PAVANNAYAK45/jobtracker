FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

EXPOSE 10000

ENTRYPOINT ["java", "-jar", "target/jobtracker-0.0.1-SNAPSHOT.jar"]