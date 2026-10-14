# Smart Clinic Management System - MySQL Database Schema Design

## 1. Overview
The MySQL database stores application data using four primary relational tables: `admin`, `doctor`, `patient`, and `appointment`.

---

## 2. Table Specifications

### 2.1 Admin (`admin`)
| Column Name | Data Type | Constraints | Description |
| :--- | :--- | :--- | :--- |
| `id` | BIGINT | PRIMARY KEY, AUTO_INCREMENT | System admin ID |
| `name` | VARCHAR(100) | NOT NULL | Admin full name |
| `email` | VARCHAR(100) | UNIQUE, NOT NULL | Login email address |
| `password` | VARCHAR(255) | NOT NULL | Password hash |

---

### 2.2 Doctor (`doctor`)
| Column Name | Data Type | Constraints | Description |
| :--- | :--- | :--- | :--- |
| `id` | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Doctor unique ID |
| `name` | VARCHAR(100) | NOT NULL | Full name |
| `specialty` | VARCHAR(100) | NOT NULL | Medical specialty |
| `email` | VARCHAR(100) | UNIQUE, NOT NULL | Email address |
| `password` | VARCHAR(255) | NOT NULL | Password hash |

---

### 2.3 Patient (`patient`)
| Column Name | Data Type | Constraints | Description |
| :--- | :--- | :--- | :--- |
| `id` | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Patient unique ID |
| `name` | VARCHAR(100) | NOT NULL | Patient name |
| `email` | VARCHAR(100) | UNIQUE, NOT NULL | Email address |
| `phone_number`| VARCHAR(20) | NOT NULL | Contact number |
| `password` | VARCHAR(255) | NOT NULL | Password hash |

---

### 2.4 Appointment (`appointment`)
| Column Name | Data Type | Constraints | Description |
| :--- | :--- | :--- | :--- |
| `id` | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Appointment ID |
| `doctor_id` | BIGINT | FOREIGN KEY (`doctor.id`) | Assigned doctor |
| `patient_id` | BIGINT | FOREIGN KEY (`patient.id`) | Patient reference |
| `appointment_time` | DATETIME | NOT NULL | Scheduled slot |
| `status` | VARCHAR(20) | NOT NULL | Booking status |
