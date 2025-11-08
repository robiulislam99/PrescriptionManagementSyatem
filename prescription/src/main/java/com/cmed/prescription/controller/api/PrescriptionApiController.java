package com.cmed.prescription.controller.api;

import com.cmed.prescription.model.Prescription;
import com.cmed.prescription.service.PrescriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Prescription API", description = "Prescription management APIs")
public class PrescriptionApiController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/prescription")
    @Operation(summary = "Get all prescriptions", description = "Returns a list of all prescriptions")
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/prescription/{id}")
    @Operation(summary = "Get prescription by ID")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}