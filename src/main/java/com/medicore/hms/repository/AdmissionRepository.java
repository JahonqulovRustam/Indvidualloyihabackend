package com.medicore.hms.repository;

import com.medicore.hms.model.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Long> {
    List<Admission> findByWardIdAndStatus(Long wardId, String status);

    List<Admission> findByPatientId(Long patientId);
}
