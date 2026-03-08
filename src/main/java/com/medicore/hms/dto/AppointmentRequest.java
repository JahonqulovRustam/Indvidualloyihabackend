package com.medicore.hms.dto;

import lombok.Data;

@Data
public class AppointmentRequest {
    private Long patientId;
    private Long doctorId;
    private String appointmentDate; // "2024-03-08"
    private String appointmentTime; // "10:30"
    private String notes;
}
