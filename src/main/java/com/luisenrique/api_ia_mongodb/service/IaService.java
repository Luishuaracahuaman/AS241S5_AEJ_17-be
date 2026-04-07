package com.luisenrique.api_ia_mongodb.service;

import com.luisenrique.api_ia_mongodb.model.IaResponse;
import com.luisenrique.api_ia_mongodb.repository.IaResponseRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class IaService {

    private final WebClient webClient;
    private final IaResponseRepository repository;

    @Value("${api.gemini.key}")
    private String geminiKey;
    @Value("${api.gemini.url}")
    private String geminiUrl;

    @Value("${api.huggingface.key}")
    private String huggingFaceKey;
    @Value("${api.huggingface.url}")
    private String huggingFaceUrl;

    // ¡AQUÍ ESTÁ EL CAMBIO!
    // Ya no pedimos el WebClient.Builder a Spring, lo construimos nosotros mismos.
    public IaService(IaResponseRepository repository) {
        this.webClient = WebClient.builder().build();
        this.repository = repository;
    }

    public Mono<IaResponse> callGemini(String prompt) {
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(Map.of("text", prompt)))));

        return webClient.post()
                .uri(geminiUrl + "?key=" + geminiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(response -> {
                    IaResponse savedResponse = IaResponse.builder()
                            .apiUsed("GEMINI")
                            .promptText(prompt)
                            .responseText(response)
                            .createdAt(LocalDateTime.now())
                            .build();
                    return repository.save(savedResponse);
                });
    }

    public Mono<IaResponse> callHuggingFace(String text) {
        Map<String, String> requestBody = Map.of("inputs", text);

        return webClient.post()
                .uri(huggingFaceUrl)
                .header("Authorization", "Bearer " + huggingFaceKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(response -> {
                    IaResponse savedResponse = IaResponse.builder()
                            .apiUsed("HUGGINGFACE")
                            .promptText(text)
                            .responseText(response)
                            .createdAt(LocalDateTime.now())
                            .build();
                    return repository.save(savedResponse);
                });
    }
}