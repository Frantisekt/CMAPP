package com.demo.serverless.infrastructure.adapters;

import com.demo.serverless.domain.model.DentalRecord;
import com.demo.serverless.domain.ports.DentalRecordRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
@Profile({"local", "dev"})
public class InMemoryDentalRecordRepository implements DentalRecordRepository {
    private final Map<UUID, DentalRecord> dentalRecords = new ConcurrentHashMap<>();

    @Override
    public DentalRecord save(DentalRecord dentalRecord) {
        dentalRecords.put(dentalRecord.getId(), dentalRecord);
        return dentalRecord;
    }

    @Override
    public Optional<DentalRecord> findById(UUID id) {
        return Optional.ofNullable(dentalRecords.get(id));
    }

    @Override
    public Optional<DentalRecord> findByPatientId(UUID patientId) {
        return dentalRecords.values().stream()
            .filter(record -> record.getPatientId().equals(patientId))
            .findFirst();
    }

    @Override
    public List<DentalRecord> findAll() {
        return new ArrayList<>(dentalRecords.values());
    }

    @Override
    public void delete(UUID id) {
        dentalRecords.remove(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return dentalRecords.containsKey(id);
    }
} 