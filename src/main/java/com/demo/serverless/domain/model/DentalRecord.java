package com.demo.serverless.domain.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DentalRecord {
    private UUID id;
    private UUID patientId;
    private String diagnosis;
    private String treatment;
    private String observations;
    private List<String> allergies;
    private List<String> medicalHistory;
    private List<String> attachments;
    private LocalDate lastVisit;
    private LocalDate nextVisit;
    private boolean active;

    public DentalRecord() {
        this.id = UUID.randomUUID();
        this.active = true;
    }

    public DentalRecord(UUID patientId, String diagnosis, String treatment, 
                       String observations, List<String> allergies, 
                       List<String> medicalHistory, List<String> attachments,
                       LocalDate lastVisit, LocalDate nextVisit) {
        this();
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.observations = observations;
        this.allergies = allergies;
        this.medicalHistory = medicalHistory;
        this.attachments = attachments;
        this.lastVisit = lastVisit;
        this.nextVisit = nextVisit;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public List<String> getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(List<String> medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public LocalDate getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDate lastVisit) {
        this.lastVisit = lastVisit;
    }

    public LocalDate getNextVisit() {
        return nextVisit;
    }

    public void setNextVisit(LocalDate nextVisit) {
        this.nextVisit = nextVisit;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
} 