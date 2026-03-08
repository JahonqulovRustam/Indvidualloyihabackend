package com.medicore.hms.service;

import com.medicore.hms.model.Ward;
import com.medicore.hms.repository.AdmissionRepository;
import com.medicore.hms.repository.WardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WardService {

    private final WardRepository repository;
    private final AdmissionRepository admissionRepository;

    public List<Ward> getAllWards() {
        List<Ward> wards = repository.findAll();
        for (Ward ward : wards) {
            int occupied = admissionRepository.findByWardIdAndStatus(ward.getId(), "ADMITTED").size();
            ward.setOccupied(occupied);
        }
        return wards;
    }

    public Ward createWard(Ward ward) {
        return repository.save(ward);
    }

    public void deleteWard(Long id) {
        repository.deleteById(id);
    }
}
