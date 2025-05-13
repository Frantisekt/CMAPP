package com.demo.serverless.application.services;

import com.demo.serverless.domain.ports.PatientRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class RecordNumberGenerator {
    private final PatientRepository patientRepository;

    public RecordNumberGenerator(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public String generateRecordNumber() {
        int year = LocalDate.now().getYear();
        int nextNumber = getNextSequentialNumber(year);
        return String.format("EXP-%d-%03d", year, nextNumber);
    }

    private int getNextSequentialNumber(int year) {
        return patientRepository.findAll().stream()
            .filter(p -> p.getRecordNumber() != null && 
                        p.getRecordNumber().startsWith("EXP-" + year))
            .map(p -> Integer.parseInt(p.getRecordNumber().split("-")[2]))
            .max(Integer::compareTo)
            .orElse(0) + 1;
    }
} 