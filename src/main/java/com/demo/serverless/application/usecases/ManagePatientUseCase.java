package com.demo.serverless.application.usecases;

import com.demo.serverless.application.services.RecordNumberGenerator;
import com.demo.serverless.domain.exceptions.PatientNotFoundException;
import com.demo.serverless.domain.model.Patient;
import com.demo.serverless.domain.ports.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ManagePatientUseCase {
    private final PatientRepository patientRepository;
    private final RecordNumberGenerator recordNumberGenerator;

    public ManagePatientUseCase(PatientRepository patientRepository,
                              RecordNumberGenerator recordNumberGenerator) {
        this.patientRepository = patientRepository;
        this.recordNumberGenerator = recordNumberGenerator;
    }

    public Patient createPatient(Patient patient) {
        patient.setRecordNumber(recordNumberGenerator.generateRecordNumber());
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Patient patient) {
        if (!patientRepository.existsById(patient.getId())) {
            throw PatientNotFoundException.byId(patient.getId().toString());
        }
        return patientRepository.save(patient);
    }

    public Patient getPatient(UUID id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> PatientNotFoundException.byId(id.toString()));
    }

    public Patient getPatientByDni(String dni) {
        return patientRepository.findByDni(dni)
                .orElseThrow(() -> PatientNotFoundException.byDni(dni));
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public void deletePatient(UUID id) {
        if (!patientRepository.existsById(id)) {
            throw PatientNotFoundException.byId(id.toString());
        }
        patientRepository.delete(id);
    }

    public Patient getPatientByRecordNumber(String recordNumber) {
        return patientRepository.findByRecordNumber(recordNumber)
                .orElseThrow(() -> PatientNotFoundException.byRecordNumber(recordNumber));
    }
} 