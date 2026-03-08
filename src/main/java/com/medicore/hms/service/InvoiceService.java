package com.medicore.hms.service;

import com.medicore.hms.model.Invoice;
import com.medicore.hms.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import com.medicore.hms.dto.InvoiceRequest;
import com.medicore.hms.model.Patient;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository repository;
    private final com.medicore.hms.repository.PatientRepository patientRepository;
    private final com.medicore.hms.repository.ServiceRepository serviceRepository;

    public List<Invoice> getAllInvoices() {
        return repository.findAll();
    }

    public Invoice createInvoice(InvoiceRequest request) {
        Patient patient = patientRepository.findById(request.patientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        com.medicore.hms.model.Service service = serviceRepository.findById(request.serviceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        Invoice invoice = Invoice.builder()
                .patient(patient)
                .service(service)
                .amount(request.amount())
                .date(request.date())
                .status(request.status() != null ? request.status() : "PENDING")
                .build();
        return repository.save(invoice);
    }

    public Invoice updateStatus(Long id, String status) {
        Invoice invoice = repository.findById(id).orElseThrow();
        invoice.setStatus(status);
        return repository.save(invoice);
    }
}
