package com.demo.serverless.interfaces.http;

import com.demo.serverless.application.usecases.ManagePatientUseCase;
import com.demo.serverless.domain.model.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final ManagePatientUseCase managePatientUseCase;

    public PatientController(ManagePatientUseCase managePatientUseCase) {
        this.managePatientUseCase = managePatientUseCase;
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(managePatientUseCase.createPatient(patient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable UUID id) {
        return ResponseEntity.ok(managePatientUseCase.getPatient(id));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<Patient> getPatientByDni(@PathVariable String dni) {
        return ResponseEntity.ok(managePatientUseCase.getPatientByDni(dni));
    }

    @GetMapping("/record/{recordNumber}")
    public ResponseEntity<Patient> getPatientByRecordNumber(@PathVariable String recordNumber) {
        return ResponseEntity.ok(managePatientUseCase.getPatientByRecordNumber(recordNumber));
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(managePatientUseCase.getAllPatients());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable UUID id,
            @RequestBody Patient patient) {
        return ResponseEntity.ok(managePatientUseCase.updatePatient(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        managePatientUseCase.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
} 