package com.demo.serverless.domain.exceptions;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(String message) {
        super(message);
    }

    public static AppointmentNotFoundException byId(String id) {
        return new AppointmentNotFoundException("No se encontró la cita con ID: " + id);
    }

    public static AppointmentNotFoundException byPatientId(String patientId) {
        return new AppointmentNotFoundException("No se encontraron citas para el paciente con ID: " + patientId);
    }

    public static AppointmentNotFoundException byDentistId(String dentistId) {
        return new AppointmentNotFoundException("No se encontraron citas para el odontólogo con ID: " + dentistId);
    }

    public static AppointmentNotFoundException byConsultationNumber(String consultationNumber) {
        return new AppointmentNotFoundException("No se encontró la cita con número de consulta: " + consultationNumber);
    }
} 