package com.medicore.hms.controller;

import com.medicore.hms.dto.AdmissionRequest;
import com.medicore.hms.model.Admission;
import com.medicore.hms.service.AdmissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admissions")
@RequiredArgsConstructor
public class AdmissionController {

    private final AdmissionService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'NURSE')")
    public ResponseEntity<List<Admission>> getAll() {
        return ResponseEntity.ok(service.getAllAdmissions());
    }

    @GetMapping("/ward/{wardId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'NURSE')")
    public ResponseEntity<List<Admission>> getActiveByWard(@PathVariable Long wardId) {
        return ResponseEntity.ok(service.getActiveAdmissionsByWard(wardId));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'NURSE')")
    public ResponseEntity<Admission> create(@Valid @RequestBody AdmissionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAdmission(request));
    }

    @PatchMapping("/{id}/discharge")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'NURSE')")
    public ResponseEntity<Admission> discharge(@PathVariable Long id, @RequestBody(required = false) AdmissionRequest request) {
        return ResponseEntity.ok(service.dischargePatient(id, request));
    }
}
