package com.medicore.hms.dto;

import java.time.LocalDate;

public record AdmissionRequest(
        Long patientId,
        Long wardId,
        LocalDate admissionDate,
        LocalDate dischargeDate,
        String status) {
}
