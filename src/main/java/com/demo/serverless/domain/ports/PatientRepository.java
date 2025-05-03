package com.demo.serverless.domain.ports;

import com.demo.serverless.domain.model.Patient;
import java.util.List;
import java.util.Optional;

public interface PatientRepository {
    Patient save(Patient patient);
    Optional<Patient> findById(String id);
    Optional<Patient> findByDni(String dni);
    List<Patient> findAll();
    void delete(String id);
    Optional<Patient> findByRecordNumber(String recordNumber);
} 