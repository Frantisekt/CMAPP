package com.demo.serverless.infrastructure.adapters;

import com.demo.serverless.domain.model.DentalRecord;
import com.demo.serverless.domain.ports.DentalRecordRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
@Profile({"local", "dev"})
public class InMemoryDentalRecordRepository implements DentalRecordRepository {
    private final Map<UUID, DentalRecord> records = new ConcurrentHashMap<>();

    @Override
    public DentalRecord save(DentalRecord dentalRecord) {
        if (dentalRecord.getId() == null) {
            dentalRecord.setId(UUID.randomUUID());
        }
        dentalRecord.setUpdatedAt(LocalDate.now());
        records.put(dentalRecord.getId(), dentalRecord);
        return dentalRecord;
    }

    @Override
    public Optional<DentalRecord> findById(UUID id) {
        return Optional.ofNullable(records.get(id));
    }

    @Override
    public Optional<DentalRecord> findByPatientId(UUID patientId) {
        return records.values().stream()
                .filter(r -> r.getPatientId().equals(patientId))
                .findFirst();
    }

    @Override
    public List<DentalRecord> findAll() {
        return new ArrayList<>(records.values());
    }

    @Override
    public void delete(UUID id) {
        records.remove(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return records.containsKey(id);
    }

    @Override
    public List<DentalRecord> findByDentistId(String dentistId) {
        return records.values().stream()
                .filter(r -> r.getVisits().stream()
                        .anyMatch(v -> v.getDentistId().equals(dentistId)))
                .collect(Collectors.toList());
    }

    @Override
    public List<DentalRecord> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return records.values().stream()
                .filter(r -> r.getVisits().stream()
                        .anyMatch(v -> !v.getDate().isBefore(startDate) && !v.getDate().isAfter(endDate)))
                .collect(Collectors.toList());
    }

    @Override
    public List<DentalRecord> findByProcedure(String procedureName) {
        return records.values().stream()
                .filter(r -> r.getProcedures().stream()
                        .anyMatch(p -> p.getName().equalsIgnoreCase(procedureName)))
                .collect(Collectors.toList());
    }

    @Override
    public List<DentalRecord> findByMedication(String medication) {
        return records.values().stream()
                .filter(r -> r.getMedications().stream()
                        .anyMatch(m -> m.equalsIgnoreCase(medication)))
                .collect(Collectors.toList());
    }

    @Override
    public List<DentalRecord> findByAllergy(String allergy) {
        return records.values().stream()
                .filter(r -> r.getAllergies().stream()
                        .anyMatch(a -> a.equalsIgnoreCase(allergy)))
                .collect(Collectors.toList());
    }

    @Override
    public void addVisit(UUID recordId, DentalRecord.DentalVisit visit) {
        DentalRecord record = records.get(recordId);
        if (record != null) {
            record.getVisits().add(visit);
            record.setUpdatedAt(LocalDate.now());
        }
    }

    @Override
    public void addProcedure(UUID recordId, DentalRecord.DentalProcedure procedure) {
        DentalRecord record = records.get(recordId);
        if (record != null) {
            record.getProcedures().add(procedure);
            record.setUpdatedAt(LocalDate.now());
        }
    }

    @Override
    public void addImage(UUID recordId, DentalRecord.DentalImage image) {
        DentalRecord record = records.get(recordId);
        if (record != null) {
            record.getImages().add(image);
            record.setUpdatedAt(LocalDate.now());
        }
    }

    @Override
    public void addPrescription(UUID recordId, DentalRecord.Prescription prescription) {
        DentalRecord record = records.get(recordId);
        if (record != null) {
            record.getPrescriptions().add(prescription);
            record.setUpdatedAt(LocalDate.now());
        }
    }

    @Override
    public void updateMedicalHistory(UUID recordId, Map<String, Object> medicalHistory) {
        DentalRecord record = records.get(recordId);
        if (record != null) {
            record.getMedicalHistory().putAll(medicalHistory);
            record.setUpdatedAt(LocalDate.now());
        }
    }

    @Override
    public void updateDentalHistory(UUID recordId, Map<String, Object> dentalHistory) {
        DentalRecord record = records.get(recordId);
        if (record != null) {
            record.getDentalHistory().putAll(dentalHistory);
            record.setUpdatedAt(LocalDate.now());
        }
    }

    @Override
    public void addAllergy(UUID recordId, String allergy) {
        DentalRecord record = records.get(recordId);
        if (record != null) {
            record.getAllergies().add(allergy);
            record.setUpdatedAt(LocalDate.now());
        }
    }

    @Override
    public void addMedication(UUID recordId, String medication) {
        DentalRecord record = records.get(recordId);
        if (record != null) {
            record.getMedications().add(medication);
            record.setUpdatedAt(LocalDate.now());
        }
    }

    @Override
    public void addNote(UUID recordId, String key, Object value) {
        DentalRecord record = records.get(recordId);
        if (record != null) {
            record.getNotes().put(key, value);
            record.setUpdatedAt(LocalDate.now());
        }
    }
} 