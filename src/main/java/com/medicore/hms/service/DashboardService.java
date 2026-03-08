package com.medicore.hms.service;

import com.medicore.hms.model.DashboardStats;
import com.medicore.hms.model.Invoice;
import com.medicore.hms.repository.PatientRepository;
import com.medicore.hms.repository.AppointmentRepository;
import com.medicore.hms.repository.InvoiceRepository;
import com.medicore.hms.repository.WardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

        private final PatientRepository patientRepository;
        private final AppointmentRepository appointmentRepository;
        private final WardRepository wardRepository;
        // BUG FIX: InvoiceRepository inject qilindi - revenue haqiqiy ma'lumotdan
        // hisoblansin
        private final InvoiceRepository invoiceRepository;

        public DashboardStats getDashboardStats() {
                long totalPatients = patientRepository.count();
                long totalWards = wardRepository.count();

                // BUG FIX: findAll() + stream() o'rniga faqat bugungi appointmentlar
                // AppointmentRepository ga between query qo'shildi
                LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
                LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);
                long appointmentsToday = appointmentRepository.findByAppointmentDateBetween(startOfDay, endOfDay)
                                .size();

                // BUG FIX: Hardcoded "$24,850" o'rniga haqiqiy invoice summasidan hisoblanadi
                LocalDate firstOfMonth = LocalDate.now().withDayOfMonth(1);
                List<Invoice> monthInvoices = invoiceRepository.findByDateAfter(firstOfMonth.minusDays(1));
                BigDecimal totalRevenue = monthInvoices.stream()
                                .map(Invoice::getAmount)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

                String revenueMtd = "$" + String.format("%,.0f", totalRevenue);

                return DashboardStats.builder()
                                .totalPatients(totalPatients)
                                .appointmentsToday(appointmentsToday)
                                .totalWards(totalWards)
                                .revenueMtd(revenueMtd)
                                .build();
        }
}