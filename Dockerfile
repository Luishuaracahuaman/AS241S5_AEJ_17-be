# ETAPA 1: Construir el proyecto
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compila el proyecto ignorando los tests para que sea más rápido
RUN mvn clean package -DskipTests

# ETAPA 2: Empaquetar para producción (Caja ligera)
FROM eclipse-temurin:17-jre
WORKDIR /app
# Copia el archivo .jar compilado de la etapa 1
COPY --from=build /app/target/*.jar app.jar
# Expone el puerto 8080
EXPOSE 8080
# Comando para encender Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]