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
📸 Screenshot: Login Page
Location: screenshots/login.png
Description: Secure authentication with username and password

📸 Screenshot: Prescription List
Location: screenshots/prescription-list.png
Description: View all prescriptions with date range filtering capabilities

📸 Screenshot: Create Prescription
Location: screenshots/create-prescription.png
Description: User-friendly form to add new prescriptions

📸 Screenshot: Edit Prescription
Location: screenshots/edit-prescription.png
Description: Modify existing prescription details

📸 Screenshot: Day-wise Report
Location: screenshots/daywise-report.png
Description: Analytics showing prescription counts grouped by day

📸 Screenshot: REST API Response
Location: screenshots/api-response.png
Description: JSON response from REST API endpoints (Browser/Postman)
Example endpoint: GET /api/prescriptions
Response format: JSON

📸 Screenshot: Drug Interactions
Location: screenshots/drug-interactions.png
Description: Integration with external drug interaction API

## Author
Md Robiul Islam
