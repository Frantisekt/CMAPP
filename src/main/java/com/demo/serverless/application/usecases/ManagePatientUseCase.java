package com.demo.serverless.application.usecases;

import com.demo.serverless.domain.exceptions.PatientNotFoundException;
import com.demo.serverless.domain.model.Patient;
import com.demo.serverless.domain.ports.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagePatientUseCase {
    private final PatientRepository patientRepository;

    public ManagePatientUseCase(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Patient patient) {
        if (patientRepository.findById(patient.getId()).isEmpty()) {
            throw PatientNotFoundException.byId(patient.getId());
        }
        return patientRepository.save(patient);
    }

    public Patient getPatient(String id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> PatientNotFoundException.byId(id));
    }

    public Patient getPatientByDni(String dni) {
        return patientRepository.findByDni(dni)
                .orElseThrow(() -> PatientNotFoundException.byDni(dni));
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public void deletePatient(String id) {
        if (patientRepository.findById(id).isEmpty()) {
            throw PatientNotFoundException.byId(id);
        }
        patientRepository.delete(id);
    }

    public Patient getPatientByRecordNumber(String recordNumber) {
        return patientRepository.findByRecordNumber(recordNumber)
                .orElseThrow(() -> PatientNotFoundException.byRecordNumber(recordNumber));
    }
} 