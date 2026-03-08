package com.medicore.hms.controller;

import com.medicore.hms.dto.AdmissionRequest;
import com.medicore.hms.model.Admission;
import com.medicore.hms.service.AdmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admissions")
@RequiredArgsConstructor
public class AdmissionController {

    private final AdmissionService service;

    @GetMapping
    public List<Admission> getAll() {
        return service.getAllAdmissions();
    }

    @GetMapping("/ward/{wardId}")
    public List<Admission> getActiveByWard(@PathVariable Long wardId) {
        return service.getActiveAdmissionsByWard(wardId);
    }

    @PostMapping
    public Admission create(@RequestBody AdmissionRequest request) {
        return service.createAdmission(request);
    }

    @PatchMapping("/{id}/discharge")
    public Admission discharge(@PathVariable Long id, @RequestBody AdmissionRequest request) {
        return service.dischargePatient(id, request);
    }
}
