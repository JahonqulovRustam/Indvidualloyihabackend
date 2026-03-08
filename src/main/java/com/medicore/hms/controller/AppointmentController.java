package com.medicore.hms.controller;

import com.medicore.hms.dto.AppointmentRequest;
import com.medicore.hms.dto.AppointmentResponse;
import com.medicore.hms.model.Appointment;
import com.medicore.hms.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @GetMapping
    public List<AppointmentResponse> getAll() {
        return service.getAllAppointments();
    }

    @PostMapping
    public AppointmentResponse create(@RequestBody AppointmentRequest request) {
        return service.createAppointment(request);
    }

    @PatchMapping("/{id}/status")
    public AppointmentResponse updateStatus(@PathVariable Long id, @RequestParam Appointment.Status status) {
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteAppointment(id);
    }
}
