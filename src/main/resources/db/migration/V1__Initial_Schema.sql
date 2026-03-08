-- V1: Initial Schema
-- BUG FIX: invoices table bu yerdan OLIB TASHLANDI
--   chunki V5 da qayta CREATE qilinmoqda va conflict keltirib chiqaradi.
--   Flyway V1 dan keyin V5 ni ham ishlatadi - ikki marta CREATE = xato.
-- BUG FIX: medical_services -> services deb o'zgartirildi
--   chunki Service.java entity @Table(name = "services") deb belgilangan.

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       full_name VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL
);

CREATE TABLE patients (
                          id SERIAL PRIMARY KEY,
                          full_name VARCHAR(255) NOT NULL,
                          date_of_birth DATE,
                          gender VARCHAR(20),
                          blood_group VARCHAR(10),
                          allergies TEXT,
                          contact_info TEXT
);

CREATE TABLE appointments (
                              id SERIAL PRIMARY KEY,
                              patient_id INTEGER REFERENCES patients(id) ON DELETE SET NULL,
                              doctor_id INTEGER REFERENCES users(id) ON DELETE SET NULL,
                              appointment_date TIMESTAMP NOT NULL,
    -- BUG FIX: status NOT NULL edi, lekin DEFAULT yo'q edi.
    -- AppointmentService createAppointment() da WAITING set qilinadi,
    -- lekin DB darajasida ham DEFAULT bo'lishi ishonchli.
                              status VARCHAR(50) NOT NULL DEFAULT 'WAITING',
                              notes TEXT
);

-- BUG FIX: Service.java @Table(name = "services") deb yozilgan,
-- shuning uchun "medical_services" emas "services" bo'lishi kerak
CREATE TABLE services (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE wards (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       type VARCHAR(100),
                       price_per_day DECIMAL(10, 2) NOT NULL,
                       capacity INTEGER NOT NULL
);
