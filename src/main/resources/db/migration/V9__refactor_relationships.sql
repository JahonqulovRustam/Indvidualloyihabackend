-- V9: Refactor relationships
-- Dropping existing invoices table because previous data used string patient/service names
DROP TABLE IF EXISTS invoices;

CREATE TABLE invoices (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL REFERENCES patients(id) ON DELETE CASCADE,
    service_id BIGINT NOT NULL REFERENCES services(id) ON DELETE CASCADE,
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL DEFAULT CURRENT_DATE,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    CONSTRAINT chk_invoice_status CHECK (status IN ('PENDING', 'PAID', 'CANCELLED'))
);

CREATE TABLE admissions (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL REFERENCES patients(id) ON DELETE CASCADE,
    ward_id BIGINT NOT NULL REFERENCES wards(id) ON DELETE CASCADE,
    admission_date DATE NOT NULL DEFAULT CURRENT_DATE,
    discharge_date DATE,
    status VARCHAR(20) NOT NULL DEFAULT 'ADMITTED',
    CONSTRAINT chk_admission_status CHECK (status IN ('ADMITTED', 'DISCHARGED', 'TRANSFERRED'))
);
