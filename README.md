# Prescription Management System

A web-based prescription management system built with Spring Boot.

## Features
- User authentication and authorization
- CRUD operations for prescriptions
- Date range filtering
- Day-wise prescription count report
- REST API endpoints
- External API integration
- Swagger documentation

## Technologies
- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- Thymeleaf
- H2 Database
- Swagger/OpenAPI

## Default Credentials
- **Admin**: username: `admin`, password: `admin123`
- **User**: username: `user`, password: `user123`

## Running the Application
```bash
./mvnw spring-boot:run
```

Access at: http://localhost:8080

## API Documentation
Swagger UI: http://localhost:8080/swagger-ui.html

## Screenshots
### 📸 Login Page
![login](https://github.com/robiulislam99/PrescriptionManagementSyatem/blob/main/screenshots/login2.png)
Description: Secure authentication with username and password

### 📸 Sign up Page
![login](https://github.com/robiulislam99/PrescriptionManagementSyatem/blob/main/screenshots/signup.png)
Description: Create new account with email

### 📸 Prescription List
![list](https://github.com/robiulislam99/PrescriptionManagementSyatem/blob/main/screenshots/prescription%20list%20with%20filter.png)
Description: View all prescriptions with date range filtering capabilities

### 📸 Create Prescription
![create](https://github.com/robiulislam99/PrescriptionManagementSyatem/blob/main/screenshots/create%20prescription%20form.png)
Description: User-friendly form to add new prescriptions

### 📸 Edit Prescription
![edit](https://github.com/robiulislam99/PrescriptionManagementSyatem/blob/main/screenshots/edit%20prescription.png)
Description: Modify existing prescription details

### 📸 Day-wise Report
![day](https://github.com/robiulislam99/PrescriptionManagementSyatem/blob/main/screenshots/day%20wise%20prescription%20count.png)
Description: Analytics showing prescription counts grouped by day

### 📸 REST API Response
![api](https://github.com/robiulislam99/PrescriptionManagementSyatem/blob/main/screenshots/REST%20API%20response.png)
Description: JSON response from REST API endpoints (Browser/Postman)
Example endpoint: GET /api/prescriptions
Response format: JSON


## Author
Md Robiul Islam
