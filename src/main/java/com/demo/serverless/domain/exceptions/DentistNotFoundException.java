package com.demo.serverless.domain.exceptions;

public class DentistNotFoundException extends RuntimeException {
    public DentistNotFoundException(String message) {
        super(message);
    }

    public static DentistNotFoundException byId(String id) {
        return new DentistNotFoundException("No se encontró el odontólogo con ID: " + id);
    }

    public static DentistNotFoundException byLicenseNumber(String licenseNumber) {
        return new DentistNotFoundException("No se encontró el odontólogo con matrícula: " + licenseNumber);
    }

    public static DentistNotFoundException bySpecialty(String specialty) {
        return new DentistNotFoundException("No se encontraron odontólogos con especialidad: " + specialty);
    }
} 