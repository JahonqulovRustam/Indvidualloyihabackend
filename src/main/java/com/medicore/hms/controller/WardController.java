package com.medicore.hms.controller;

import com.medicore.hms.model.Ward;
import com.medicore.hms.service.WardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/wards")
@RequiredArgsConstructor
public class WardController {

    private final WardService service;

    @GetMapping
    public List<Ward> getAll() {
        return service.getAllWards();
    }

    @PostMapping
    public Ward create(@RequestBody Ward ward) {
        return service.createWard(ward);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteWard(id);
    }
}
