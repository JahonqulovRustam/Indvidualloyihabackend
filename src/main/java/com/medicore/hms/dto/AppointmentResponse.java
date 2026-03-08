package com.medicore.hms.dto;

import com.medicore.hms.model.Appointment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppointmentResponse {
    private Long id;
    private Long patientId;
    private String patientName;
    private Long doctorId;
    private String doctorName;
    private String appointmentDate;
    private String appointmentTime;
    private String status;
    private String notes;

    public static AppointmentResponse from(Appointment a) {
        String date = "";
        String time = "";
        if (a.getAppointmentDate() != null) {
            date = a.getAppointmentDate().toLocalDate().toString();
            time = a.getAppointmentDate().toLocalTime().toString();
        }
        return AppointmentResponse.builder()
                .id(a.getId())
                .patientId(a.getPatient() != null ? a.getPatient().getId() : null)
                .patientName(a.getPatient() != null ? a.getPatient().getFullName() : "")
                .doctorId(a.getDoctor() != null ? a.getDoctor().getId() : null)
                .doctorName(a.getDoctor() != null ? a.getDoctor().getFullName() : "")
                .appointmentDate(date)
                .appointmentTime(time)
                .status(a.getStatus() != null ? a.getStatus().name() : "")
                .notes(a.getNotes())
                .build();
    }
}
