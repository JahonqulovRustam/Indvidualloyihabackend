package com.medicore.hms.controller;

import com.medicore.hms.service.ServiceService;
import lombok.RequiredArgsConstructor;
import com.medicore.hms.model.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService service;

    @GetMapping
    public List<Service> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Service create(@RequestBody Service serviceEntity) {
        return service.create(serviceEntity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
