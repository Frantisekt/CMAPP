package com.demo.serverless.infrastructure.adapters;

import com.demo.serverless.domain.model.Treatment;
import com.demo.serverless.domain.ports.TreatmentRepository;
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
public class InMemoryTreatmentRepository implements TreatmentRepository {
    private final Map<UUID, Treatment> treatments = new ConcurrentHashMap<>();

    @Override
    public Treatment save(Treatment treatment) {
        treatments.put(treatment.getId(), treatment);
        return treatment;
    }

    @Override
    public Optional<Treatment> findById(UUID id) {
        return Optional.ofNullable(treatments.get(id));
    }

    @Override
    public List<Treatment> findAll() {
        return new ArrayList<>(treatments.values());
    }

    @Override
    public List<Treatment> findByCategory(String category) {
        return treatments.values().stream()
            .filter(t -> t.getCategory().equals(category))
            .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        treatments.remove(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return treatments.containsKey(id);
    }
} 