package com.cmed.prescription.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "prescriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Prescription date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate prescriptionDate;

    @NotBlank(message = "Patient name is required")
    @Column(nullable = false)
    private String patientName;

    @NotNull(message = "Patient age is required")
    @Min(value = 0, message = "Age must be at least 0")
    @Max(value = 150, message = "Age must be less than 150")
    @Column(nullable = false)
    private Integer patientAge;

    @NotBlank(message = "Gender is required")
    @Column(nullable = false)
    private String patientGender; // Male, Female, Other

    @Column(columnDefinition = "TEXT")
    private String diagnosis;

    @Column(columnDefinition = "TEXT")
    private String medicines;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nextVisitDate;

    @Column(nullable = false)
    private LocalDate createdAt = LocalDate.now();
}