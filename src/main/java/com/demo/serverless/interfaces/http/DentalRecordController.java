package com.demo.serverless.interfaces.http;

import com.demo.serverless.application.usecases.ManageDentalRecordUseCase;
import com.demo.serverless.domain.model.DentalRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dental-records")
public class DentalRecordController {
    private final ManageDentalRecordUseCase manageDentalRecordUseCase;

    public DentalRecordController(ManageDentalRecordUseCase manageDentalRecordUseCase) {
        this.manageDentalRecordUseCase = manageDentalRecordUseCase;
    }

    @PostMapping
    public ResponseEntity<DentalRecord> createDentalRecord(@RequestBody DentalRecord record) {
        return ResponseEntity.ok(manageDentalRecordUseCase.createDentalRecord(record));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentalRecord> updateDentalRecord(@PathVariable String id, @RequestBody DentalRecord record) {
        record.setId(id);
        return ResponseEntity.ok(manageDentalRecordUseCase.updateDentalRecord(record));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentalRecord> getDentalRecord(@PathVariable String id) {
        return ResponseEntity.ok(manageDentalRecordUseCase.getDentalRecord(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<DentalRecord>> getPatientDentalRecords(@PathVariable String patientId) {
        return ResponseEntity.ok(manageDentalRecordUseCase.getPatientDentalRecords(patientId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDentalRecord(@PathVariable String id) {
        manageDentalRecordUseCase.deleteDentalRecord(id);
        return ResponseEntity.ok().build();
    }
} 