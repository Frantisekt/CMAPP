package com.demo.serverless.domain.exceptions;

public class TreatmentNotFoundException extends RuntimeException {
    public TreatmentNotFoundException(String message) {
        super(message);
    }

    public static TreatmentNotFoundException byId(String id) {
        return new TreatmentNotFoundException("No se encontró el tratamiento con ID: " + id);
    }

    public static TreatmentNotFoundException byCategory(String category) {
        return new TreatmentNotFoundException("No se encontraron tratamientos en la categoría: " + category);
    }
} 