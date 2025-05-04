package com.demo.serverless.domain.ports;

import com.demo.serverless.domain.model.Patient;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientRepository {
    Patient save(Patient patient);
    Optional<Patient> findById(UUID id);
    Optional<Patient> findByDni(String dni);
    Optional<Patient> findByRecordNumber(String recordNumber);
    List<Patient> findAll();
    void delete(UUID id);
    boolean existsById(UUID id);
} 