package com.demo.serverless.application.usecases;

import com.demo.serverless.domain.exceptions.DentalRecordNotFoundException;
import com.demo.serverless.domain.exceptions.PatientNotFoundException;
import com.demo.serverless.domain.model.DentalRecord;
import com.demo.serverless.domain.model.Patient;
import com.demo.serverless.domain.ports.DentalRecordRepository;
import com.demo.serverless.domain.ports.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageDentalRecordUseCase {
    private final DentalRecordRepository dentalRecordRepository;
    private final PatientRepository patientRepository;

    public ManageDentalRecordUseCase(DentalRecordRepository dentalRecordRepository, PatientRepository patientRepository) {
        this.dentalRecordRepository = dentalRecordRepository;
        this.patientRepository = patientRepository;
    }

    public DentalRecord createDentalRecord(DentalRecord record) {
        // Verificar que el paciente existe
        if (patientRepository.findById(record.getPatientId()).isEmpty()) {
            throw PatientNotFoundException.byId(record.getPatientId());
        }
        return dentalRecordRepository.save(record);
    }

    public DentalRecord updateDentalRecord(DentalRecord record) {
        // Verificar que el registro existe
        if (dentalRecordRepository.findById(record.getId()).isEmpty()) {
            throw DentalRecordNotFoundException.byId(record.getId());
        }
        return dentalRecordRepository.save(record);
    }

    public DentalRecord getDentalRecord(String id) {
        return dentalRecordRepository.findById(id)
                .orElseThrow(() -> DentalRecordNotFoundException.byId(id));
    }

    public List<DentalRecord> getPatientDentalRecords(String patientId) {
        // Verificar que el paciente existe
        if (patientRepository.findById(patientId).isEmpty()) {
            throw PatientNotFoundException.byId(patientId);
        }
        List<DentalRecord> records = dentalRecordRepository.findByPatientId(patientId);
        if (records.isEmpty()) {
            throw DentalRecordNotFoundException.byPatientId(patientId);
        }
        return records;
    }

    public void deleteDentalRecord(String id) {
        if (dentalRecordRepository.findById(id).isEmpty()) {
            throw DentalRecordNotFoundException.byId(id);
        }
        dentalRecordRepository.delete(id);
    }
} 