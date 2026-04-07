# Servicios Cognitivos - Integración de Gemini y Hugging Face con Spring Boot

Servicios Cognitivos integrando las APIs de **Google Gemini** y **Hugging Face**. El primer modelo (Gemini) recibe instrucciones de texto (prompts) y genera contenido conversacional de alta calidad. El segundo modelo (DistilBERT de Hugging Face) analiza el texto en inglés y determina automáticamente el sentimiento de la frase (Positivo o Negativo). Mediante programación reactiva con WebClient, nuestra API procesa estas consultas, devuelve los resultados al cliente y almacena automáticamente el historial de cada petición en una base de datos NoSQL en la nube.

### 1. Servicios Cognitivos
* **Google AI Studio** - Modelo Gemini 2.5 Flash (Generación de Contenido)
* **Hugging Face API** - Modelo DistilBERT (Análisis de Sentimientos)

### 2. Spring Boot
* **Java:** JDK 17
* **IDE:** Visual Studio Code
* **Gestor:** Apache Maven
* **Framework:** Spring Boot

### 3. Dependencias de Maven:
* Spring Boot Starter WebFlux
* Spring Boot Starter Data MongoDB Reactive
* Lombok
* Reactor Test

---

### Dependencias Spring WebFlux + MongoDB (NoSQL)

**Spring WebFlux | Datos MongoDB reactivos | Proyecto Reactor | Lombok**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb-reactive</artifactId>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>

<dependency>
    <groupId>io.projectreactor</groupId>
    <artifactId>reactor-test</artifactId>
    <scope>test</scope>
</dependency>
