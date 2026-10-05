package com.medicore.hms.service;

import com.medicore.hms.dto.AdmissionRequest;
import com.medicore.hms.exception.ConflictException;
import com.medicore.hms.exception.ResourceNotFoundException;
import com.medicore.hms.model.Admission;
import com.medicore.hms.model.Patient;
import com.medicore.hms.model.Ward;
import com.medicore.hms.repository.AdmissionRepository;
import com.medicore.hms.repository.PatientRepository;
import com.medicore.hms.repository.WardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmissionService {

    private final AdmissionRepository repository;
    private final PatientRepository patientRepository;
    private final WardRepository wardRepository;

    public List<Admission> getAllAdmissions() {
        return repository.findAll();
    }

    public List<Admission> getActiveAdmissionsByWard(Long wardId) {
        return repository.findByWardIdAndStatus(wardId, "ADMITTED");
    }

    public Admission createAdmission(AdmissionRequest request) {
        Patient patient = patientRepository.findById(request.patientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + request.patientId()));
        Ward ward = wardRepository.findById(request.wardId())
                .orElseThrow(() -> new ResourceNotFoundException("Ward not found with id: " + request.wardId()));

        int currentlyOccupied = repository.findByWardIdAndStatus(ward.getId(), "ADMITTED").size();
        if (currentlyOccupied >= ward.getCapacity()) {
            throw new ConflictException("Ward '" + ward.getName() + "' is at full capacity (" + ward.getCapacity() + " beds)");
        }

        Admission admission = Admission.builder()
                .patient(patient)
                .ward(ward)
                .admissionDate(request.admissionDate() != null ? request.admissionDate() : LocalDate.now())
                .status(request.status() != null ? request.status() : "ADMITTED")
                .build();
        return repository.save(admission);
    }

    public Admission dischargePatient(Long id, AdmissionRequest request) {
        Admission admission = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admission not found with id: " + id));
        admission.setStatus("DISCHARGED");
        admission.setDischargeDate(request != null && request.dischargeDate() != null ? request.dischargeDate() : LocalDate.now());
        return repository.save(admission);
    }
}
