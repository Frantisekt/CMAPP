package com.demo.serverless.domain.ports;

import com.demo.serverless.domain.model.Treatment;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TreatmentRepository {
    Treatment save(Treatment treatment);
    Optional<Treatment> findById(UUID id);
    List<Treatment> findAll();
    List<Treatment> findByCategory(String category);
    void delete(UUID id);
    boolean existsById(UUID id);
} 