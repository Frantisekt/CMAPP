package com.demo.serverless.interfaces.http;

import com.demo.serverless.application.usecases.ManageDentalRecordUseCase;
import com.demo.serverless.domain.model.DentalRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dental-records")
public class DentalRecordController {
    private final ManageDentalRecordUseCase manageDentalRecordUseCase;

    public DentalRecordController(ManageDentalRecordUseCase manageDentalRecordUseCase) {
        this.manageDentalRecordUseCase = manageDentalRecordUseCase;
    }

    @PostMapping
    public ResponseEntity<DentalRecord> createDentalRecord(@RequestBody DentalRecord dentalRecord) {
        return ResponseEntity.ok(manageDentalRecordUseCase.createDentalRecord(dentalRecord));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentalRecord> getDentalRecordById(@PathVariable UUID id) {
        return ResponseEntity.ok(manageDentalRecordUseCase.getDentalRecord(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<DentalRecord> getDentalRecordByPatientId(@PathVariable UUID patientId) {
        return ResponseEntity.ok(manageDentalRecordUseCase.getDentalRecordByPatientId(patientId));
    }

    @GetMapping
    public ResponseEntity<List<DentalRecord>> getAllDentalRecords() {
        return ResponseEntity.ok(manageDentalRecordUseCase.getAllDentalRecords());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentalRecord> updateDentalRecord(
            @PathVariable UUID id,
            @RequestBody DentalRecord dentalRecord) {
        return ResponseEntity.ok(manageDentalRecordUseCase.updateDentalRecord(id, dentalRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDentalRecord(@PathVariable UUID id) {
        manageDentalRecordUseCase.deleteDentalRecord(id);
        return ResponseEntity.noContent().build();
    }
} 