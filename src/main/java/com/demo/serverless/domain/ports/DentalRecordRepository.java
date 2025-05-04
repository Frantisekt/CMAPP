package com.demo.serverless.domain.ports;

import com.demo.serverless.domain.model.DentalRecord;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DentalRecordRepository {
    DentalRecord save(DentalRecord dentalRecord);
    Optional<DentalRecord> findById(UUID id);
    Optional<DentalRecord> findByPatientId(UUID patientId);
    List<DentalRecord> findAll();
    void delete(UUID id);
    boolean existsById(UUID id);
} 