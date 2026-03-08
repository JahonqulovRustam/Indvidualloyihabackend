-- V5: Create Invoices Table
-- BUG FIX 1: V1 da invoices CREATE qilingan edi - bu conflict beradi.
--   V1 dan invoices olib tashlandi, faqat shu yerda yaratiladi.
-- BUG FIX 2: Invoice.java modeli bilan ustunlar moslantirildi:
--   - patient_id o'rniga patient_name (Invoice.java da patient_name bor, patient_id yo'q)
--   - service (text maydon)
--   - amount (total_amount emas)
--   - date (created_at emas - LocalDate ishlatiladi)
-- BUG FIX 3: V1 da ham invoices bor edi, ikki marta "CREATE TABLE invoices" xato.

CREATE TABLE invoices (
                          id BIGSERIAL PRIMARY KEY,
    -- Invoice.java: private String patientName
                          patient_name VARCHAR(255) NOT NULL,
    -- Invoice.java: private String service
                          service VARCHAR(255) NOT NULL,
    -- Invoice.java: private BigDecimal amount
                          amount DECIMAL(10, 2) NOT NULL,
    -- Invoice.java: private LocalDate date
                          date DATE NOT NULL DEFAULT CURRENT_DATE,
    -- Invoice.java: private String status
                          status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
                          CONSTRAINT chk_invoice_status CHECK (status IN ('PENDING', 'PAID', 'CANCELLED'))
);
