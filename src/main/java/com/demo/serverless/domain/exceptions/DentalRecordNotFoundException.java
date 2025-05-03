package com.demo.serverless.domain.exceptions;

public class DentalRecordNotFoundException extends RuntimeException {
    public DentalRecordNotFoundException(String message) {
        super(message);
    }

    public static DentalRecordNotFoundException byId(String id) {
        return new DentalRecordNotFoundException("No se encontró el registro dental con ID: " + id);
    }

    public static DentalRecordNotFoundException byPatientId(String patientId) {
        return new DentalRecordNotFoundException("No se encontraron registros dentales para el paciente con ID: " + patientId);
    }
} 