package com.demo.serverless.infrastructure.adapters;

import com.demo.serverless.domain.model.Patient;
import com.demo.serverless.domain.ports.PatientRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
@Profile({"local", "dev"})
public class InMemoryPatientRepository implements PatientRepository {
    private final Map<UUID, Patient> patients = new ConcurrentHashMap<>();
    private final AtomicInteger recordCounter = new AtomicInteger(0);
    private static final DateTimeFormatter YEAR_FORMATTER = DateTimeFormatter.ofPattern("yyyy");

    @Override
    public Patient save(Patient patient) {
        if (patient.getId() == null) {
            patient.setId(UUID.randomUUID());
            // Generar número de expediente solo para nuevos pacientes
            if (patient.getRecordNumber() == null) {
                //String year = LocalDateTime.now().format(YEAR_FORMATTER);
                //String recordNumber = String.format("EXP-%s-%04d", year, recordCounter.incrementAndGet());
                String recordNumber = String.valueOf(recordCounter.incrementAndGet());
                patient.setRecordNumber(recordNumber);
            }
        }
        patients.put(patient.getId(), patient);
        return patient;
    }

    @Override
    public Optional<Patient> findById(UUID id) {
        return Optional.ofNullable(patients.get(id));
    }

    @Override
    public Optional<Patient> findByDni(String dni) {
        return patients.values().stream()
                .filter(p -> p.getDni().equals(dni))
                .findFirst();
    }

    @Override
    public List<Patient> findAll() {
        return new ArrayList<>(patients.values());
    }

    @Override
    public void delete(UUID id) {
        patients.remove(id);
    }

    @Override
    public Optional<Patient> findByRecordNumber(String recordNumber) {
        return patients.values().stream()
                .filter(p -> p.getRecordNumber().equals(recordNumber))
                .findFirst();
    }

    @Override
    public boolean existsById(UUID id) {
        return patients.containsKey(id);
    }
} 