# 🤖 API Integradora de Inteligencia Artificial con Spring Boot y MongoDB

Este proyecto es una API REST construida con **Spring Boot (Reactivo)** que actúa como intermediario para consumir servicios de Inteligencia Artificial de terceros (**Google Gemini** y **Hugging Face**). Además, implementa persistencia de datos almacenando el historial de consultas y respuestas en una base de datos en la nube usando **MongoDB Atlas**.

## 🚀 Características Principales

* **Integración con Google Gemini:** Permite enviar *prompts* al modelo `gemini-2.5-flash` para generar respuestas de texto natural.
* **Análisis de Sentimientos (NLP):** Integración con Hugging Face usando el modelo `distilbert-base-uncased-finetuned-sst-2-english` para analizar si un texto tiene una connotación positiva o negativa.
* **Programación Reactiva:** Uso de `WebClient` de Spring WebFlux para realizar peticiones HTTP asíncronas y eficientes a las APIs externas.
* **Persistencia en la Nube:** Cada interacción (API usada, prompt, respuesta y fecha de creación) se guarda automáticamente en una colección de **MongoDB Atlas**.

## 🛠️ Tecnologías Utilizadas

* **Java 17**
* **Spring Boot 4.0.5**
* **Spring WebFlux** (para peticiones reactivas con WebClient)
* **Spring Data MongoDB Reactive** (para la conexión asíncrona a la base de datos)
* **MongoDB Atlas** (Base de datos NoSQL en la nube)
* **Maven** (Gestor de dependencias)

## ⚙️ Configuración y Ejecución

Para correr este proyecto localmente, necesitas tener Java 17 instalado.

### 1. Clonar el repositorio
```bash
git clone [https://github.com/TuUsuario/api-ia-mongodb.git](https://github.com/TuUsuario/api-ia-mongodb.git)
cd api-ia-mongodb
