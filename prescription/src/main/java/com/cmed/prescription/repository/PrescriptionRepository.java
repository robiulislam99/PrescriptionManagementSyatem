package com.cmed.prescription.repository;

import com.cmed.prescription.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    List<Prescription> findByPrescriptionDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT p.prescriptionDate as date, COUNT(p) as count " +
            "FROM Prescription p " +
            "GROUP BY p.prescriptionDate " +
            "ORDER BY p.prescriptionDate")
    List<Map<String, Object>> getDayWisePrescriptionCount();
}