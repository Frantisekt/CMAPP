package com.demo.serverless.domain.exceptions;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String message) {
        super(message);
    }

    public static PatientNotFoundException byId(String id) {
        return new PatientNotFoundException("No se encontró el paciente con ID: " + id);
    }

    public static PatientNotFoundException byRecordNumber(String recordNumber) {
        return new PatientNotFoundException("No se encontró el paciente con número de expediente: " + recordNumber);
    }

    public static PatientNotFoundException byDni(String dni) {
        return new PatientNotFoundException("No se encontró el paciente con DNI: " + dni);
    }
} 