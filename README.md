# 🤖 Servicios Cognitivos - Integración de Gemini y Hugging Face con Spring Boot

Servicios Cognitivos integrando las APIs de **Google Gemini** y **Hugging Face**. El primer modelo (Gemini) recibe instrucciones de texto (prompts) y genera contenido conversacional de alta calidad. El segundo modelo (DistilBERT de Hugging Face) analiza el texto en inglés y determina automáticamente el sentimiento de la frase (Positivo o Negativo). Mediante programación reactiva con WebClient, nuestra API procesa estas consultas, devuelve los resultados al cliente y almacena automáticamente el historial de cada petición en una base de datos NoSQL en la nube.

### 1. Servicios Cognitivos
* <img src="https://upload.wikimedia.org/wikipedia/commons/8/8a/Google_Gemini_logo.svg" width="18"/> **Google AI Studio** - Modelo Gemini 2.5 Flash (Generación de Contenido)
* <img src="https://huggingface.co/front/assets/huggingface_logo-noborder.svg" width="18"/> **Hugging Face API** - Modelo DistilBERT (Análisis de Sentimientos)

### 2. Tecnologías y Herramientas
* <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="18"/> **Java:** JDK 17
* <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/vscode/vscode-original.svg" width="18"/> **IDE:** Visual Studio Code
* <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/maven/maven-original.svg" width="18"/> **Gestor:** Apache Maven
* <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="18"/> **Framework:** Spring Boot
* <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mongodb/mongodb-original.svg" width="18"/> **Base de Datos:** MongoDB Atlas

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
