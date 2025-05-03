package com.demo.serverless.domain.ports;

import com.demo.serverless.domain.model.DentalRecord;
import java.util.List;
import java.util.Optional;

public interface DentalRecordRepository {
    DentalRecord save(DentalRecord record);
    Optional<DentalRecord> findById(String id);
    List<DentalRecord> findByPatientId(String patientId);
    void delete(String id);
} 