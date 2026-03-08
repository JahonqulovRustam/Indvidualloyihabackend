package com.medicore.hms.controller;

import com.medicore.hms.model.Invoice;
import com.medicore.hms.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService service;

    @GetMapping
    public List<Invoice> getAll() {
        return service.getAllInvoices();
    }

    @PostMapping
    public Invoice create(@RequestBody com.medicore.hms.dto.InvoiceRequest request) {
        return service.createInvoice(request);
    }

    @PatchMapping("/{id}/status")
    public Invoice updateStatus(@PathVariable Long id, @RequestParam String status) {
        return service.updateStatus(id, status);
    }
}
