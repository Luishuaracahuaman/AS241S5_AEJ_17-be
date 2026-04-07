package com.luisenrique.api_ia_mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "ia_responses")
public class IaResponse {

    @Id
    private String id;
    private String apiUsed; // Ejemplo: "GEMINI" o "HUGGINGFACE"
    private String promptText; // Lo que le enviamos a la IA
    private String responseText; // Lo que respondió la IA
    private LocalDateTime createdAt;
}