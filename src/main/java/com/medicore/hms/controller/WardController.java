package com.medicore.hms.controller;

import com.medicore.hms.model.Ward;
import com.medicore.hms.service.WardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wards")
@RequiredArgsConstructor
public class WardController {

    private final WardService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'NURSE', 'CASHIER')")
    public ResponseEntity<List<Ward>> getAll() {
        return ResponseEntity.ok(service.getAllWards());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Ward> create(@Valid @RequestBody Ward ward) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createWard(ward));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteWard(id);
        return ResponseEntity.noContent().build();
    }
}
