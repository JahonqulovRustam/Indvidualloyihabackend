package com.medicore.hms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentRequest {

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    @NotBlank(message = "Appointment date is required (YYYY-MM-DD)")
    private String appointmentDate;

    @NotBlank(message = "Appointment time is required (HH:mm)")
    private String appointmentTime;

    private String notes;
}
