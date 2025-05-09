package com.demo.serverless.application.usecases;

import com.demo.serverless.domain.exceptions.TreatmentNotFoundException;
import com.demo.serverless.domain.model.Treatment;
import com.demo.serverless.domain.ports.TreatmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ManageTreatmentUseCase {
    private final TreatmentRepository treatmentRepository;

    public ManageTreatmentUseCase(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public Treatment createTreatment(Treatment treatment) {
        validateTreatment(treatment);
        return treatmentRepository.save(treatment);
    }

    public Treatment getTreatment(UUID id) {
        return treatmentRepository.findById(id)
            .orElseThrow(() -> TreatmentNotFoundException.byId(id.toString()));
    }

    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }

    public List<Treatment> getTreatmentsByCategory(String category) {
        List<Treatment> treatments = treatmentRepository.findByCategory(category);
        if (treatments.isEmpty()) {
            throw TreatmentNotFoundException.byCategory(category);
        }
        return treatments;
    }

    public Treatment updateTreatment(UUID id, Treatment treatment) {
        if (!treatmentRepository.existsById(id)) {
            throw TreatmentNotFoundException.byId(id.toString());
        }
        validateTreatment(treatment);
        treatment.setId(id);
        return treatmentRepository.save(treatment);
    }

    public void deleteTreatment(UUID id) {
        if (!treatmentRepository.existsById(id)) {
            throw TreatmentNotFoundException.byId(id.toString());
        }
        treatmentRepository.delete(id);
    }

    private void validateTreatment(Treatment treatment) {
        if (treatment.getName() == null || treatment.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del tratamiento es requerido");
        }
        if (treatment.getCategory() == null || treatment.getCategory().trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría del tratamiento es requerida");
        }
        if (treatment.getCost() < 0) {
            throw new IllegalArgumentException("El costo del tratamiento no puede ser negativo");
        }
    }
} 