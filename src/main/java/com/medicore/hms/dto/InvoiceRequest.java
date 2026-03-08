package com.medicore.hms.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InvoiceRequest(
        Long patientId,
        Long serviceId,
        BigDecimal amount,
        LocalDate date,
        String status) {
}
