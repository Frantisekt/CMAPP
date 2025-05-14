package com.demo.serverless.domain.ports;

import com.demo.serverless.domain.model.DentalRecord;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;

public interface DentalRecordRepository {
    DentalRecord save(DentalRecord dentalRecord);
    Optional<DentalRecord> findById(UUID id);
    Optional<DentalRecord> findByPatientId(UUID patientId);
    List<DentalRecord> findAll();
    void delete(UUID id);
    boolean existsById(UUID id);
    
    // Métodos específicos para búsquedas NoSQL
    List<DentalRecord> findByDentistId(String dentistId);
    List<DentalRecord> findByDateRange(LocalDate startDate, LocalDate endDate);
    List<DentalRecord> findByProcedure(String procedureName);
    List<DentalRecord> findByMedication(String medication);
    List<DentalRecord> findByAllergy(String allergy);
    
    // Métodos para actualizaciones parciales
    void addVisit(UUID recordId, DentalRecord.DentalVisit visit);
    void addProcedure(UUID recordId, DentalRecord.DentalProcedure procedure);
    void addImage(UUID recordId, DentalRecord.DentalImage image);
    void addPrescription(UUID recordId, DentalRecord.Prescription prescription);
    void updateMedicalHistory(UUID recordId, Map<String, Object> medicalHistory);
    void updateDentalHistory(UUID recordId, Map<String, Object> dentalHistory);
    void addAllergy(UUID recordId, String allergy);
    void addMedication(UUID recordId, String medication);
    void addNote(UUID recordId, String key, Object value);
} 