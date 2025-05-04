package com.demo.serverless.domain.ports;

import com.demo.serverless.domain.model.Dentist;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DentistRepository {
    Dentist save(Dentist dentist);
    Optional<Dentist> findById(UUID id);
    Optional<Dentist> findByLicenseNumber(String licenseNumber);
    List<Dentist> findAll();
    List<Dentist> findBySpecialty(String specialty);
    void delete(UUID id);
    boolean existsById(UUID id);
} 