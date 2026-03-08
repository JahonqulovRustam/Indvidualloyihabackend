package com.medicore.hms.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class RecommendationResponse {
    private String diagnosis;
    private String generalAdvice;
    private List<MedicationDto> medications;

    @Data
    @Builder
    public static class MedicationDto {
        private String name;
        private String dosage;
        private String frequency;
        private String duration;
    }
}
