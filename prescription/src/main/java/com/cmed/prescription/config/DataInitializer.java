package com.cmed.prescription.config;

import com.cmed.prescription.model.User;
import com.cmed.prescription.model.Prescription;
import com.cmed.prescription.repository.UserRepository;
import com.cmed.prescription.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository,
                                      PrescriptionRepository prescriptionRepository,
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            // Create default users
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ROLE_ADMIN");
                admin.setEnabled(true);
                userRepository.save(admin);

                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRole("ROLE_USER");
                user.setEnabled(true);
                userRepository.save(user);

                System.out.println("✅ Default users created:");
                System.out.println("   Admin - Username: admin, Password: admin123");
                System.out.println("   User  - Username: user, Password: user123");
            }

            // Create sample prescriptions
            if (prescriptionRepository.count() == 0) {
                Prescription p1 = new Prescription();
                p1.setPrescriptionDate(LocalDate.now().minusDays(5));
                p1.setPatientName("John Doe");
                p1.setPatientAge(35);
                p1.setPatientGender("Male");
                p1.setDiagnosis("Common cold with fever");
                p1.setMedicines("Paracetamol 500mg - 1 tablet 3 times daily\nCetirizine 10mg - 1 tablet at night");
                p1.setNextVisitDate(LocalDate.now().plusDays(7));
                prescriptionRepository.save(p1);

                Prescription p2 = new Prescription();
                p2.setPrescriptionDate(LocalDate.now().minusDays(3));
                p2.setPatientName("Jane Smith");
                p2.setPatientAge(28);
                p2.setPatientGender("Female");
                p2.setDiagnosis("Migraine headache");
                p2.setMedicines("Sumatriptan 50mg - As needed\nIbuprofen 400mg - 1 tablet every 6 hours");
                p2.setNextVisitDate(LocalDate.now().plusDays(14));
                prescriptionRepository.save(p2);

                Prescription p3 = new Prescription();
                p3.setPrescriptionDate(LocalDate.now().minusDays(1));
                p3.setPatientName("Ahmed Rahman");
                p3.setPatientAge(45);
                p3.setPatientGender("Male");
                p3.setDiagnosis("Hypertension");
                p3.setMedicines("Amlodipine 5mg - 1 tablet daily\nAtenolol 50mg - 1 tablet daily");
                p3.setNextVisitDate(LocalDate.now().plusDays(30));
                prescriptionRepository.save(p3);

                Prescription p4 = new Prescription();
                p4.setPrescriptionDate(LocalDate.now());
                p4.setPatientName("Fatima Begum");
                p4.setPatientAge(32);
                p4.setPatientGender("Female");
                p4.setDiagnosis("Allergic rhinitis");
                p4.setMedicines("Montelukast 10mg - 1 tablet at bedtime\nFluticasone nasal spray - 2 sprays each nostril daily");
                p4.setNextVisitDate(LocalDate.now().plusDays(21));
                prescriptionRepository.save(p4);

                Prescription p5 = new Prescription();
                p5.setPrescriptionDate(LocalDate.now().minusDays(7));
                p5.setPatientName("Karim Hossain");
                p5.setPatientAge(55);
                p5.setPatientGender("Male");
                p5.setDiagnosis("Type 2 Diabetes Mellitus");
                p5.setMedicines("Metformin 500mg - 1 tablet twice daily with meals\nGlimepiride 2mg - 1 tablet in the morning");
                p5.setNextVisitDate(LocalDate.now().plusDays(60));
                prescriptionRepository.save(p5);

                System.out.println("✅ Sample prescriptions created: " + prescriptionRepository.count() + " records");
            }
        };
    }
}