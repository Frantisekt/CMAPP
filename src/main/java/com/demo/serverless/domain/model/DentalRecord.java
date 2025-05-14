package com.demo.serverless.domain.model;

import java.time.LocalDate;
import java.util.*;

public class DentalRecord {
    private UUID id;
    private UUID patientId;
    private Map<String, Object> personalInfo;
    private List<DentalVisit> visits;
    private List<DentalProcedure> procedures;
    private List<DentalImage> images;
    private List<Prescription> prescriptions;
    private Map<String, Object> medicalHistory;
    private Map<String, Object> dentalHistory;
    private List<String> allergies;
    private List<String> medications;
    private Map<String, Object> notes;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private boolean active;

    public DentalRecord() {
        this.id = UUID.randomUUID();
        this.active = true;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.personalInfo = new HashMap<>();
        this.visits = new ArrayList<>();
        this.procedures = new ArrayList<>();
        this.images = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
        this.medicalHistory = new HashMap<>();
        this.dentalHistory = new HashMap<>();
        this.allergies = new ArrayList<>();
        this.medications = new ArrayList<>();
        this.notes = new HashMap<>();
    }

    // Clases internas para estructurar mejor los datos
    public static class DentalVisit {
        private UUID id;
        private LocalDate date;
        private String reason;
        private String diagnosis;
        private String treatment;
        private String observations;
        private String dentistId;
        private Map<String, Object> additionalInfo;

        public DentalVisit() {
            this.id = UUID.randomUUID();
            this.additionalInfo = new HashMap<>();
        }

        // Getters y Setters
        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public LocalDate getDate() { return date; }
        public void setDate(LocalDate date) { this.date = date; }
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
        public String getDiagnosis() { return diagnosis; }
        public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
        public String getTreatment() { return treatment; }
        public void setTreatment(String treatment) { this.treatment = treatment; }
        public String getObservations() { return observations; }
        public void setObservations(String observations) { this.observations = observations; }
        public String getDentistId() { return dentistId; }
        public void setDentistId(String dentistId) { this.dentistId = dentistId; }
        public Map<String, Object> getAdditionalInfo() { return additionalInfo; }
        public void setAdditionalInfo(Map<String, Object> additionalInfo) { this.additionalInfo = additionalInfo; }
    }

    public static class DentalProcedure {
        private UUID id;
        private String name;
        private String description;
        private LocalDate date;
        private String status;
        private String dentistId;
        private Map<String, Object> details;

        public DentalProcedure() {
            this.id = UUID.randomUUID();
            this.details = new HashMap<>();
        }

        // Getters y Setters
        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public LocalDate getDate() { return date; }
        public void setDate(LocalDate date) { this.date = date; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getDentistId() { return dentistId; }
        public void setDentistId(String dentistId) { this.dentistId = dentistId; }
        public Map<String, Object> getDetails() { return details; }
        public void setDetails(Map<String, Object> details) { this.details = details; }
    }

    public static class DentalImage {
        private UUID id;
        private String url;
        private String type;
        private String description;
        private LocalDate date;
        private Map<String, Object> metadata;

        public DentalImage() {
            this.id = UUID.randomUUID();
            this.metadata = new HashMap<>();
        }

        // Getters y Setters
        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public LocalDate getDate() { return date; }
        public void setDate(LocalDate date) { this.date = date; }
        public Map<String, Object> getMetadata() { return metadata; }
        public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }
    }

    public static class Prescription {
        private UUID id;
        private LocalDate date;
        private String medication;
        private String dosage;
        private String frequency;
        private String duration;
        private String notes;
        private String dentistId;

        public Prescription() {
            this.id = UUID.randomUUID();
        }

        // Getters y Setters
        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public LocalDate getDate() { return date; }
        public void setDate(LocalDate date) { this.date = date; }
        public String getMedication() { return medication; }
        public void setMedication(String medication) { this.medication = medication; }
        public String getDosage() { return dosage; }
        public void setDosage(String dosage) { this.dosage = dosage; }
        public String getFrequency() { return frequency; }
        public void setFrequency(String frequency) { this.frequency = frequency; }
        public String getDuration() { return duration; }
        public void setDuration(String duration) { this.duration = duration; }
        public String getNotes() { return notes; }
        public void setNotes(String notes) { this.notes = notes; }
        public String getDentistId() { return dentistId; }
        public void setDentistId(String dentistId) { this.dentistId = dentistId; }
    }

    // Getters y Setters principales
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getPatientId() { return patientId; }
    public void setPatientId(UUID patientId) { this.patientId = patientId; }
    public Map<String, Object> getPersonalInfo() { return personalInfo; }
    public void setPersonalInfo(Map<String, Object> personalInfo) { this.personalInfo = personalInfo; }
    public List<DentalVisit> getVisits() { return visits; }
    public void setVisits(List<DentalVisit> visits) { this.visits = visits; }
    public List<DentalProcedure> getProcedures() { return procedures; }
    public void setProcedures(List<DentalProcedure> procedures) { this.procedures = procedures; }
    public List<DentalImage> getImages() { return images; }
    public void setImages(List<DentalImage> images) { this.images = images; }
    public List<Prescription> getPrescriptions() { return prescriptions; }
    public void setPrescriptions(List<Prescription> prescriptions) { this.prescriptions = prescriptions; }
    public Map<String, Object> getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(Map<String, Object> medicalHistory) { this.medicalHistory = medicalHistory; }
    public Map<String, Object> getDentalHistory() { return dentalHistory; }
    public void setDentalHistory(Map<String, Object> dentalHistory) { this.dentalHistory = dentalHistory; }
    public List<String> getAllergies() { return allergies; }
    public void setAllergies(List<String> allergies) { this.allergies = allergies; }
    public List<String> getMedications() { return medications; }
    public void setMedications(List<String> medications) { this.medications = medications; }
    public Map<String, Object> getNotes() { return notes; }
    public void setNotes(Map<String, Object> notes) { this.notes = notes; }
    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }
    public LocalDate getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDate updatedAt) { this.updatedAt = updatedAt; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
} 