-- V8: Fix services table
-- It's possible that the old V1 migration created "medical_services" instead of "services",
-- and Flyway did not re-run V1. This script will rename it if it exists, or create it.

DO $$
BEGIN
    IF EXISTS (
        SELECT FROM information_schema.tables 
        WHERE table_schema = 'public' 
        AND table_name = 'medical_services'
    ) THEN
        ALTER TABLE medical_services RENAME TO services;
    ELSE
        CREATE TABLE IF NOT EXISTS services (
            id SERIAL PRIMARY KEY,
            name VARCHAR(255) NOT NULL,
            description TEXT,
            price DECIMAL(10, 2) NOT NULL
        );
    END IF;
END $$;
