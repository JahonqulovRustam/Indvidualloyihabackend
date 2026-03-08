package com.medicore.hms.service;

import com.medicore.hms.model.Service;          // model klassi
import com.medicore.hms.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;

@org.springframework.stereotype.Service         // to‘liq nom bilan yozildi
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository repository;

    public List<Service> getAll() {
        return repository.findAll();
    }

    public Service create(Service service) {
        return repository.save(service);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}