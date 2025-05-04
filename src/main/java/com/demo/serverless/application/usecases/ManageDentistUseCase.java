package com.demo.serverless.application.usecases;

import com.demo.serverless.domain.exceptions.DentistNotFoundException;
import com.demo.serverless.domain.model.Dentist;
import com.demo.serverless.domain.ports.DentistRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ManageDentistUseCase {
    private final DentistRepository dentistRepository;

    public ManageDentistUseCase(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    public Dentist createDentist(Dentist dentist) {
        if (dentistRepository.findByLicenseNumber(dentist.getLicenseNumber()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un odontólogo con esta matrícula");
        }
        return dentistRepository.save(dentist);
    }

    public Dentist getDentistById(UUID id) {
        return dentistRepository.findById(id)
            .orElseThrow(() -> DentistNotFoundException.byId(id.toString()));
    }

    public Dentist getDentistByLicenseNumber(String licenseNumber) {
        return dentistRepository.findByLicenseNumber(licenseNumber)
            .orElseThrow(() -> DentistNotFoundException.byLicenseNumber(licenseNumber));
    }

    public List<Dentist> getAllDentists() {
        List<Dentist> dentists = dentistRepository.findAll();
        if (dentists.isEmpty()) {
            throw DentistNotFoundException.bySpecialty("No hay odontólogos registrados");
        }
        return dentists;
    }

    public List<Dentist> getDentistsBySpecialty(String specialty) {
        List<Dentist> dentists = dentistRepository.findBySpecialty(specialty);
        if (dentists.isEmpty()) {
            throw DentistNotFoundException.bySpecialty(specialty);
        }
        return dentists;
    }

    public Dentist updateDentist(UUID id, Dentist updatedDentist) {
        if (!dentistRepository.existsById(id)) {
            throw DentistNotFoundException.byId(id.toString());
        }
        updatedDentist.setId(id);
        return dentistRepository.save(updatedDentist);
    }

    public void deleteDentist(UUID id) {
        if (!dentistRepository.existsById(id)) {
            throw DentistNotFoundException.byId(id.toString());
        }
        dentistRepository.delete(id);
    }
} 