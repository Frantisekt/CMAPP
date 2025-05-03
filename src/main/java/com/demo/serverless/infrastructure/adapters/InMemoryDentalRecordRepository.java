package com.demo.serverless.infrastructure.adapters;

import com.demo.serverless.domain.model.DentalRecord;
import com.demo.serverless.domain.ports.DentalRecordRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
@Profile({"local", "dev"})
public class InMemoryDentalRecordRepository implements DentalRecordRepository {
    private final ConcurrentHashMap<String, DentalRecord> records = new ConcurrentHashMap<>();

    @Override
    public DentalRecord save(DentalRecord record) {
        if (record.getId() == null) {
            record.setId(java.util.UUID.randomUUID().toString());
        }
        records.put(record.getId(), record);
        return record;
    }

    @Override
    public Optional<DentalRecord> findById(String id) {
        return Optional.ofNullable(records.get(id));
    }

    @Override
    public List<DentalRecord> findByPatientId(String patientId) {
        return records.values().stream()
                .filter(record -> record.getPatientId().equals(patientId))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        records.remove(id);
    }
} 