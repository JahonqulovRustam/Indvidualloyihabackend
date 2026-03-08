-- V7: Fix invoices table schema by dropping and recreating
-- This ensures the table in the database exactly matches the Invoice.java entity

DROP TABLE IF EXISTS invoices;

CREATE TABLE invoices (
    id BIGSERIAL PRIMARY KEY,
    patient_name VARCHAR(255) NOT NULL,
    service VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL DEFAULT CURRENT_DATE,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    CONSTRAINT chk_invoice_status CHECK (status IN ('PENDING', 'PAID', 'CANCELLED'))
);
