package com.medicore.hms.service;

import com.medicore.hms.dto.AdmissionRequest;
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
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Ward ward = wardRepository.findById(request.wardId())
                .orElseThrow(() -> new RuntimeException("Ward not found"));

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
                .orElseThrow(() -> new RuntimeException("Admission not found"));
        admission.setStatus("DISCHARGED");
        admission.setDischargeDate(request.dischargeDate() != null ? request.dischargeDate() : LocalDate.now());
        return repository.save(admission);
    }

}
