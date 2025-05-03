package com.demo.serverless.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class DentalRecord {
    private String id;
    private String patientId;
    private LocalDateTime date;
    private String diagnosis;
    private String treatment;
    private String observations;
    private List<String> allergies;
    private List<String> medicalHistory;
    private List<String> attachments;

    public DentalRecord() {
    }

    public DentalRecord(String id, String patientId, LocalDateTime date, String diagnosis, String treatment, 
                       String observations, List<String> allergies, List<String> medicalHistory, 
                       List<String> attachments) {
        this.id = id;
        this.patientId = patientId;
        this.date = date;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.observations = observations;
        this.allergies = allergies;
        this.medicalHistory = medicalHistory;
        this.attachments = attachments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
} 