package com.cmed.prescription.controller;

import com.cmed.prescription.model.Prescription;
import com.cmed.prescription.service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping
    public String listPrescriptions(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            Model model) {

        LocalDate start;
        LocalDate end;

        if (startDate == null || endDate == null) {
            // Default to current month
            YearMonth currentMonth = YearMonth.now();
            start = currentMonth.atDay(1);
            end = currentMonth.atEndOfMonth();
        } else {
            start = LocalDate.parse(startDate);
            end = LocalDate.parse(endDate);
        }

        List<Prescription> prescriptions = prescriptionService.getPrescriptionsByDateRange(start, end);

        model.addAttribute("prescriptions", prescriptions);
        model.addAttribute("startDate", start);
        model.addAttribute("endDate", end);

        return "prescriptions/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("prescription", new Prescription());
        return "prescriptions/form";
    }

    @PostMapping("/save")
    public String savePrescription(@Valid @ModelAttribute Prescription prescription,
                                   BindingResult result,
                                   Model model) {
        if (result.hasErrors()) {
            return "prescriptions/form";
        }

        prescriptionService.savePrescription(prescription);
        return "redirect:/prescriptions";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Prescription prescription = prescriptionService.getPrescriptionById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));

        model.addAttribute("prescription", prescription);
        return "prescriptions/form";
    }

    @GetMapping("/delete/{id}")
    public String deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return "redirect:/prescriptions";
    }

    @GetMapping("/report")
    public String showReport(Model model) {
        try {
            List<Map<String, Object>> report = prescriptionService.getDayWisePrescriptionCount();
            model.addAttribute("report", report);
            System.out.println("📊 Report data loaded: " + report.size() + " entries");

            // Debug: Print first entry if available
            if (!report.isEmpty()) {
                System.out.println("   First entry: " + report.get(0));
            }

            return "prescriptions/report";
        } catch (Exception e) {
            System.err.println("❌ Error loading report: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("report", List.of()); // Empty list to prevent null errors
            model.addAttribute("error", "Error generating report: " + e.getMessage());
            return "prescriptions/report";
        }
    }
}