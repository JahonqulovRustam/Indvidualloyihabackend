package com.medicore.hms.service;

import com.medicore.hms.exception.ResourceNotFoundException;
import com.medicore.hms.model.Patient;
import com.medicore.hms.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repository;

    public List<Patient> getAllPatients() {
        return repository.findAll();
    }

    public Page<Patient> getAllPatientsPaged(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Patient createPatient(Patient patient) {
        return repository.save(patient);
    }

    public Patient getPatientById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = getPatientById(id);
        patient.setFullName(patientDetails.getFullName());
        patient.setContactInfo(patientDetails.getContactInfo());
        patient.setAllergies(patientDetails.getAllergies());
        patient.setDateOfBirth(patientDetails.getDateOfBirth());
        patient.setGender(patientDetails.getGender());
        patient.setBloodGroup(patientDetails.getBloodGroup());
        return repository.save(patient);
    }

    public void deletePatient(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
