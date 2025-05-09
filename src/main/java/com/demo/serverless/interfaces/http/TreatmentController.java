package com.demo.serverless.interfaces.http;

import com.demo.serverless.application.usecases.ManageTreatmentUseCase;
import com.demo.serverless.domain.model.Treatment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {
    private final ManageTreatmentUseCase manageTreatmentUseCase;

    public TreatmentController(ManageTreatmentUseCase manageTreatmentUseCase) {
        this.manageTreatmentUseCase = manageTreatmentUseCase;
    }

    @PostMapping
    public ResponseEntity<Treatment> createTreatment(@RequestBody Treatment treatment) {
        return ResponseEntity.ok(manageTreatmentUseCase.createTreatment(treatment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treatment> getTreatmentById(@PathVariable UUID id) {
        return ResponseEntity.ok(manageTreatmentUseCase.getTreatment(id));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Treatment>> getTreatmentsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(manageTreatmentUseCase.getTreatmentsByCategory(category));
    }

    @GetMapping
    public ResponseEntity<List<Treatment>> getAllTreatments() {
        return ResponseEntity.ok(manageTreatmentUseCase.getAllTreatments());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Treatment> updateTreatment(
            @PathVariable UUID id,
            @RequestBody Treatment treatment) {
        return ResponseEntity.ok(manageTreatmentUseCase.updateTreatment(id, treatment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable UUID id) {
        manageTreatmentUseCase.deleteTreatment(id);
        return ResponseEntity.noContent().build();
    }
} 