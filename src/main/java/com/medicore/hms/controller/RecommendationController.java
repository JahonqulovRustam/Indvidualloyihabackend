package com.medicore.hms.controller;

import com.medicore.hms.dto.RecommendationResponse;
import com.medicore.hms.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService service;

    @GetMapping("/conditions")
    public ResponseEntity<List<String>> getConditions() {
        return ResponseEntity.ok(service.getSupportedConditions());
    }

    @GetMapping
    public ResponseEntity<RecommendationResponse> getRecommendation(@RequestParam String diagnosis) {
        if (diagnosis == null || diagnosis.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.getRecommendations(diagnosis));
    }
}
