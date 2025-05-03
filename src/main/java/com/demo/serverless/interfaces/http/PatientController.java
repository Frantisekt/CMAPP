package com.demo.serverless.interfaces.http;

import com.demo.serverless.application.usecases.ManagePatientUseCase;
import com.demo.serverless.domain.model.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable String id, @RequestBody Patient patient) {
        patient.setId(id);
        return ResponseEntity.ok(managePatientUseCase.updatePatient(patient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable String id) {
        return ResponseEntity.ok(managePatientUseCase.getPatient(id));
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<Patient> getPatientByDni(@PathVariable String dni) {
        return ResponseEntity.ok(managePatientUseCase.getPatientByDni(dni));
    }

    @GetMapping("/record-number/{recordNumber}")
    public ResponseEntity<Patient> getPatientByRecordNumber(@PathVariable String recordNumber) {
        return ResponseEntity.ok(managePatientUseCase.getPatientByRecordNumber(recordNumber));
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(managePatientUseCase.getAllPatients());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable String id) {
        managePatientUseCase.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
} 