package com.demo.serverless.application.usecases;

import com.demo.serverless.domain.exceptions.DentalRecordNotFoundException;
import com.demo.serverless.domain.exceptions.PatientNotFoundException;
import com.demo.serverless.domain.model.DentalRecord;
import com.demo.serverless.domain.ports.DentalRecordRepository;
import com.demo.serverless.domain.ports.PatientRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
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

    private void validateDentalRecord(DentalRecord dentalRecord) {
        if (!patientRepository.existsById(dentalRecord.getPatientId())) {
            throw PatientNotFoundException.byId(dentalRecord.getPatientId().toString());
        }

        if (dentalRecord.getLastVisit() != null && dentalRecord.getNextVisit() != null 
            && dentalRecord.getLastVisit().isAfter(dentalRecord.getNextVisit())) {
            throw new IllegalArgumentException("La fecha de la última visita no puede ser posterior a la próxima visita");
        }

        if (dentalRecord.getDiagnosis() == null || dentalRecord.getDiagnosis().trim().isEmpty()) {
            throw new IllegalArgumentException("El diagnóstico es requerido");
        }

        if (dentalRecord.getTreatment() == null || dentalRecord.getTreatment().trim().isEmpty()) {
            throw new IllegalArgumentException("El tratamiento es requerido");
        }
    }
} 