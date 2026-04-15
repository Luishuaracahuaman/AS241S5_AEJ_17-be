package com.luisenrique.api_ia_mongodb.service;

import com.luisenrique.api_ia_mongodb.model.IaResponse;
import com.luisenrique.api_ia_mongodb.repository.IaResponseRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings("unchecked") // Evita las advertencias amarillas de Java
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

    public IaService(IaResponseRepository repository) {
        this.webClient = WebClient.builder().build();
        this.repository = repository;
    }

    public Mono<IaResponse> callGemini(String prompt) {
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(Map.of("text", prompt)))));

        return webClient.post()
                .uri(URI.create(geminiUrl))
                .header("x-goog-api-key", geminiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class) // Usamos Map de Java nativo
                .flatMap(responseMap -> {

                    String textoLimpio = "";
                    try {
                        List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseMap
                                .get("candidates");
                        Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
                        List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
                        textoLimpio = (String) parts.get(0).get("text");
                    } catch (Exception e) {
                        textoLimpio = "Error procesando el texto, pero la conexión fue exitosa.";
                    }

                    IaResponse savedResponse = IaResponse.builder()
                            .apiUsed("GEMINI")
                            .promptText(prompt)
                            .responseText(textoLimpio)
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
                .bodyToMono(List.class)
                .flatMap(responseList -> {

                    String sentimientoLimpio = "";
                    try {

                        List<Map<String, Object>> resultados = (List<Map<String, Object>>) responseList.get(0);
                        Map<String, Object> mejorResultado = resultados.get(0);
                        sentimientoLimpio = (String) mejorResultado.get("label");
                    } catch (Exception e) {
                        sentimientoLimpio = "Error al limpiar JSON. Conexión exitosa.";
                    }

                    IaResponse savedResponse = IaResponse.builder()
                            .apiUsed("HUGGINGFACE")
                            .promptText(text)
                            .responseText(sentimientoLimpio)
                            .createdAt(LocalDateTime.now())
                            .build();
                    return repository.save(savedResponse);
                });
    }
}