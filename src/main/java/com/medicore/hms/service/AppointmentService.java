package com.medicore.hms.service;

import com.medicore.hms.dto.AppointmentRequest;
import com.medicore.hms.dto.AppointmentResponse;
import com.medicore.hms.model.Appointment;
import com.medicore.hms.model.Patient;
import com.medicore.hms.model.User;
import com.medicore.hms.repository.AppointmentRepository;
import com.medicore.hms.repository.PatientRepository;
import com.medicore.hms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository repository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public List<AppointmentResponse> getAllAppointments() {
        return repository.findAll().stream()
                .map(AppointmentResponse::from)
                .collect(Collectors.toList());
    }

    public AppointmentResponse createAppointment(AppointmentRequest request) {
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + request.getPatientId()));

        User doctor = userRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + request.getDoctorId()));

        LocalDate date = LocalDate.parse(request.getAppointmentDate());
        LocalTime time = LocalTime.parse(request.getAppointmentTime());
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        Appointment appointment = Appointment.builder()
                .patient(patient)
                .doctor(doctor)
                .appointmentDate(dateTime)
                .status(Appointment.Status.WAITING)
                .notes(request.getNotes())
                .build();

        return AppointmentResponse.from(repository.save(appointment));
    }

    public AppointmentResponse updateStatus(Long id, Appointment.Status status) {
        Appointment appointment = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Appointment not found with id: " + id));
        appointment.setStatus(status);
        return AppointmentResponse.from(repository.save(appointment));
    }

    public void deleteAppointment(Long id) {
        repository.deleteById(id);
    }
}
