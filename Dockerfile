# Etapa 1: Construcción
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Producción
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Variables de entorno vacías por defecto (Seguridad para que no use el application.yml directamente)
ENV SPRING_DATA_MONGODB_URI=""
ENV API_GEMINI_KEY=""
ENV API_HUGGINGFACE_KEY=""

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]