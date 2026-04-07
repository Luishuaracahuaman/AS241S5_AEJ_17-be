package com.luisenrique.api_ia_mongodb.controller;

import com.luisenrique.api_ia_mongodb.model.IaResponse;
import com.luisenrique.api_ia_mongodb.service.IaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/ia")
public class IaController {

    private final IaService iaService;

    public IaController(IaService iaService) {
        this.iaService = iaService;
    }

    @GetMapping("/gemini")
    public Mono<IaResponse> askGemini(@RequestParam String prompt) {
        return iaService.callGemini(prompt);
    }

    @GetMapping("/sentiment")
    public Mono<IaResponse> analyzeSentiment(@RequestParam String text) {
        return iaService.callHuggingFace(text);
    }
}