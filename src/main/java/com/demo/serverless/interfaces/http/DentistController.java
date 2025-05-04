package com.demo.serverless.interfaces.http;

import com.demo.serverless.application.usecases.ManageDentistUseCase;
import com.demo.serverless.domain.model.Dentist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dentists")
public class DentistController {
    private final ManageDentistUseCase manageDentistUseCase;

    public DentistController(ManageDentistUseCase manageDentistUseCase) {
        this.manageDentistUseCase = manageDentistUseCase;
    }

    @PostMapping
    public ResponseEntity<Dentist> createDentist(@RequestBody Dentist dentist) {
        return ResponseEntity.ok(manageDentistUseCase.createDentist(dentist));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> getDentistById(@PathVariable UUID id) {
        return ResponseEntity.ok(manageDentistUseCase.getDentistById(id));
    }

    @GetMapping("/license/{licenseNumber}")
    public ResponseEntity<Dentist> getDentistByLicenseNumber(@PathVariable String licenseNumber) {
        return ResponseEntity.ok(manageDentistUseCase.getDentistByLicenseNumber(licenseNumber));
    }

    @GetMapping("/specialty/{specialty}")
    public ResponseEntity<List<Dentist>> getDentistsBySpecialty(@PathVariable String specialty) {
        return ResponseEntity.ok(manageDentistUseCase.getDentistsBySpecialty(specialty));
    }

    @GetMapping
    public ResponseEntity<List<Dentist>> getAllDentists() {
        return ResponseEntity.ok(manageDentistUseCase.getAllDentists());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dentist> updateDentist(
            @PathVariable UUID id,
            @RequestBody Dentist dentist) {
        return ResponseEntity.ok(manageDentistUseCase.updateDentist(id, dentist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDentist(@PathVariable UUID id) {
        manageDentistUseCase.deleteDentist(id);
        return ResponseEntity.noContent().build();
    }
} 