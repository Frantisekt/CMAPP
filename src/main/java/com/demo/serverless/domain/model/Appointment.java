package com.demo.serverless.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {
    private UUID id;
    private UUID patientId;
    private UUID dentistId;
    private LocalDateTime dateTime;
    private String status; // SCHEDULED, CANCELLED, COMPLETED
    private String consultationNumber;
    private String notes;

    public Appointment() {
        this.id = UUID.randomUUID();
        this.status = "SCHEDULED";
    }

    public Appointment(UUID patientId, UUID dentistId, LocalDateTime dateTime, 
                      String consultationNumber, String notes) {
        this();
        this.patientId = patientId;
        this.dentistId = dentistId;
        this.dateTime = dateTime;
        this.consultationNumber = consultationNumber;
        this.notes = notes;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getPatientId() { return patientId; }
    public void setPatientId(UUID patientId) { this.patientId = patientId; }
    public UUID getDentistId() { return dentistId; }
    public void setDentistId(UUID dentistId) { this.dentistId = dentistId; }
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getConsultationNumber() { return consultationNumber; }
    public void setConsultationNumber(String consultationNumber) { this.consultationNumber = consultationNumber; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
} 