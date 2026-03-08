# MediCore HMS - Backend

MediCore Hospital Management System (HMS) backend built with Spring Boot.

## Features
- **Authentication & Security**: JWT-based authentication with role-based access control (ADMIN, DOCTOR, NURSE, CASHIER).
- **Patient Management**: Complete CRUD for patient records.
- **Appointment Scheduling**: Manage appointments between patients and doctors.
- **Billing & Invoices**: Generate and track invoices linked to patients and services.
- **Ward Management**: Track ward capacity and patient admissions.
- **AI Recommendations**: Diagnosis-based medical advice and medication suggestions.

## Tech Stack
- **Java 21**
- **Spring Boot 3.4.3**
- **Spring Security (JWT)**
- **Spring Data JPA**
- **PostgreSQL**
- **Flyway** (Database Migrations)
- **Lombok**

## Getting Started

### Prerequisites
- JDK 21 or higher
- PostgreSQL 16 or higher
- Maven 3.9.x

### Database Setup
1. Create a PostgreSQL database named `Medicore_hms`.
2. Configure your environment variables or update `src/main/resources/application.properties`:
   ```properties
   DB_URL=jdbc:postgresql://localhost:5432/Medicore_hms
   DB_USERNAME=your_username
   DB_PASSWORD=your_password
   ```

### Running the Application
```bash
mvn clean install
mvn spring-boot:run
```

The API will be available at `http://localhost:8080/api/v1`.

### Default Credentials (Seeded)
- **Admin**: `admin` / `Admin@123`
- **Doctor**: `doctor1` / `1234`
- **Nurse**: `nurse1` / `1234`

## API Endpoints
- `/api/v1/auth/**` - Authentication and Registration
- `/api/v1/patients/**` - Patient Management
- `/api/v1/appointments/**` - Appointments
- `/api/v1/invoices/**` - Billing
- `/api/v1/wards/**` - Ward Management
- `/api/v1/admissions/**` - Patient Admissions
- `/api/v1/recommendations/**` - Medical Recommendations
- `/api/v1/analytics/dashboard` - Statistics for Dashboard
