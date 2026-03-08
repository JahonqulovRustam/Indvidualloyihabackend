package com.medicore.hms.service;

import com.medicore.hms.model.Patient;
import com.medicore.hms.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repository;

    public List<Patient> getAllPatients() {
        return repository.findAll();
    }

    public Patient createPatient(Patient patient) {
        return repository.save(patient);
    }

    public Patient getPatientById(Long id) {
        // BUG FIX: orElseThrow() o'rniga aniq xato xabari
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + id));
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = getPatientById(id);
        patient.setFullName(patientDetails.getFullName());
        patient.setContactInfo(patientDetails.getContactInfo());
        patient.setAllergies(patientDetails.getAllergies());
        // BUG FIX: "// Update other fields..." comment qoldirilgan edi,
        // qolgan maydonlar ham yangilanmagan edi
        patient.setDateOfBirth(patientDetails.getDateOfBirth());
        patient.setGender(patientDetails.getGender());
        patient.setBloodGroup(patientDetails.getBloodGroup());
        return repository.save(patient);
    }

    public void deletePatient(Long id) {
        repository.deleteById(id);
    }
}
