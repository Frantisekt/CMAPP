package com.demo.serverless.infrastructure.adapters;

import com.demo.serverless.domain.model.Dentist;
import com.demo.serverless.domain.ports.DentistRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryDentistRepository implements DentistRepository {
    private final Map<UUID, Dentist> dentists = new ConcurrentHashMap<>();

    @Override
    public Dentist save(Dentist dentist) {
        dentists.put(dentist.getId(), dentist);
        return dentist;
    }

    @Override
    public Optional<Dentist> findById(UUID id) {
        return Optional.ofNullable(dentists.get(id));
    }

    @Override
    public Optional<Dentist> findByLicenseNumber(String licenseNumber) {
        return dentists.values().stream()
            .filter(d -> d.getLicenseNumber().equals(licenseNumber))
            .findFirst();
    }

    @Override
    public List<Dentist> findAll() {
        return new ArrayList<>(dentists.values());
    }

    @Override
    public List<Dentist> findBySpecialty(String specialty) {
        return dentists.values().stream()
            .filter(d -> d.getSpecialty().equals(specialty))
            .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        dentists.remove(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return dentists.containsKey(id);
    }
} 