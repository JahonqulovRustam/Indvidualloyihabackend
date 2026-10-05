package com.medicore.hms.service;

import com.medicore.hms.dto.AppointmentRequest;
import com.medicore.hms.dto.AppointmentResponse;
import com.medicore.hms.exception.ConflictException;
import com.medicore.hms.exception.ResourceNotFoundException;
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
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + request.getPatientId()));

        User doctor = userRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + request.getDoctorId()));

        LocalDate date = LocalDate.parse(request.getAppointmentDate());
        LocalTime time = LocalTime.parse(request.getAppointmentTime());
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        // Check if doctor is already booked at this exact time
        boolean isBooked = repository.existsByDoctorIdAndAppointmentDateAndStatusNot(
                doctor.getId(), dateTime, Appointment.Status.CANCELLED);
        if (isBooked) {
            throw new ConflictException("Doctor " + doctor.getFullName() + " is already booked at " + dateTime);
        }

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
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
        appointment.setStatus(status);
        return AppointmentResponse.from(repository.save(appointment));
    }

    public void deleteAppointment(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Appointment not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
