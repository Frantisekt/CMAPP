package com.demo.serverless.application.usecases;

import com.demo.serverless.domain.exceptions.DentalRecordNotFoundException;
import com.demo.serverless.domain.exceptions.PatientNotFoundException;
import com.demo.serverless.domain.model.DentalRecord;
import com.demo.serverless.domain.ports.DentalRecordRepository;
import com.demo.serverless.domain.ports.PatientRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ManageDentalRecordUseCase {
    private final DentalRecordRepository dentalRecordRepository;
    private final PatientRepository patientRepository;

    public ManageDentalRecordUseCase(DentalRecordRepository dentalRecordRepository, PatientRepository patientRepository) {
        this.dentalRecordRepository = dentalRecordRepository;
        this.patientRepository = patientRepository;
    }

    public DentalRecord createDentalRecord(DentalRecord dentalRecord) {
        validateDentalRecord(dentalRecord);
        return dentalRecordRepository.save(dentalRecord);
    }

    public DentalRecord getDentalRecord(UUID id) {
        return dentalRecordRepository.findById(id)
            .orElseThrow(() -> DentalRecordNotFoundException.byId(id.toString()));
    }

    public DentalRecord getDentalRecordByPatientId(UUID patientId) {
        return dentalRecordRepository.findByPatientId(patientId)
            .orElseThrow(() -> DentalRecordNotFoundException.byPatientId(patientId.toString()));
    }

    public List<DentalRecord> getAllDentalRecords() {
        return dentalRecordRepository.findAll();
    }

    public DentalRecord updateDentalRecord(UUID id, DentalRecord dentalRecord) {
        if (!dentalRecordRepository.existsById(id)) {
            throw DentalRecordNotFoundException.byId(id.toString());
        }
        validateDentalRecord(dentalRecord);
        dentalRecord.setId(id);
        return dentalRecordRepository.save(dentalRecord);
    }

    public void deleteDentalRecord(UUID id) {
        if (!dentalRecordRepository.existsById(id)) {
            throw DentalRecordNotFoundException.byId(id.toString());
        }
        dentalRecordRepository.delete(id);
    }

    // Métodos para actualizaciones parciales
    public void addVisit(UUID recordId, DentalRecord.DentalVisit visit) {
        validateRecordExists(recordId);
        dentalRecordRepository.addVisit(recordId, visit);
    }

    public void addProcedure(UUID recordId, DentalRecord.DentalProcedure procedure) {
        validateRecordExists(recordId);
        dentalRecordRepository.addProcedure(recordId, procedure);
    }

    public void addImage(UUID recordId, DentalRecord.DentalImage image) {
        validateRecordExists(recordId);
        dentalRecordRepository.addImage(recordId, image);
    }

    public void addPrescription(UUID recordId, DentalRecord.Prescription prescription) {
        validateRecordExists(recordId);
        dentalRecordRepository.addPrescription(recordId, prescription);
    }

    public void updateMedicalHistory(UUID recordId, Map<String, Object> medicalHistory) {
        validateRecordExists(recordId);
        dentalRecordRepository.updateMedicalHistory(recordId, medicalHistory);
    }

    public void updateDentalHistory(UUID recordId, Map<String, Object> dentalHistory) {
        validateRecordExists(recordId);
        dentalRecordRepository.updateDentalHistory(recordId, dentalHistory);
    }

    public void addAllergy(UUID recordId, String allergy) {
        validateRecordExists(recordId);
        dentalRecordRepository.addAllergy(recordId, allergy);
    }

    public void addMedication(UUID recordId, String medication) {
        validateRecordExists(recordId);
        dentalRecordRepository.addMedication(recordId, medication);
    }

    public void addNote(UUID recordId, String key, Object value) {
        validateRecordExists(recordId);
        dentalRecordRepository.addNote(recordId, key, value);
    }

    // Métodos de búsqueda
    public List<DentalRecord> findByDentistId(String dentistId) {
        return dentalRecordRepository.findByDentistId(dentistId);
    }

    public List<DentalRecord> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return dentalRecordRepository.findByDateRange(startDate, endDate);
    }

    public List<DentalRecord> findByProcedure(String procedureName) {
        return dentalRecordRepository.findByProcedure(procedureName);
    }

    public List<DentalRecord> findByMedication(String medication) {
        return dentalRecordRepository.findByMedication(medication);
    }

    public List<DentalRecord> findByAllergy(String allergy) {
        return dentalRecordRepository.findByAllergy(allergy);
    }

    private void validateDentalRecord(DentalRecord dentalRecord) {
        if (dentalRecord.getPatientId() == null) {
            throw new IllegalArgumentException("El ID del paciente es requerido");
        }
        if (!patientRepository.existsById(dentalRecord.getPatientId())) {
            throw PatientNotFoundException.byId(dentalRecord.getPatientId().toString());
        }

        // Validar que al menos haya una visita inicial
        if (dentalRecord.getVisits() == null || dentalRecord.getVisits().isEmpty()) {
            throw new IllegalArgumentException("Se requiere al menos una visita inicial");
        }

        // Validar que la primera visita tenga diagnóstico y tratamiento
        DentalRecord.DentalVisit firstVisit = dentalRecord.getVisits().get(0);
        if (firstVisit.getDiagnosis() == null || firstVisit.getDiagnosis().trim().isEmpty()) {
            throw new IllegalArgumentException("El diagnóstico de la visita inicial es requerido");
        }
        if (firstVisit.getTreatment() == null || firstVisit.getTreatment().trim().isEmpty()) {
            throw new IllegalArgumentException("El tratamiento de la visita inicial es requerido");
        }
    }

    private void validateRecordExists(UUID recordId) {
        if (!dentalRecordRepository.existsById(recordId)) {
            throw DentalRecordNotFoundException.byId(recordId.toString());
        }
    }
} 