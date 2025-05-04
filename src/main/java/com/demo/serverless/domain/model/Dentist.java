package com.demo.serverless.domain.model;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class Dentist {
    private UUID id;
    private String licenseNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String specialty;
    private List<WorkingHours> workingHours;
    private boolean active;

    public Dentist() {
        this.id = UUID.randomUUID();
        this.active = true;
    }

    public Dentist(String licenseNumber, String firstName, String lastName, String email, 
                  String phone, String specialty, List<WorkingHours> workingHours) {
        this();
        this.licenseNumber = licenseNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.specialty = specialty;
        this.workingHours = workingHours;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public List<WorkingHours> getWorkingHours() { return workingHours; }
    public void setWorkingHours(List<WorkingHours> workingHours) { this.workingHours = workingHours; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public static class WorkingHours {
        private String dayOfWeek;
        private LocalTime startTime;
        private LocalTime endTime;

        public WorkingHours() {}

        public WorkingHours(String dayOfWeek, LocalTime startTime, LocalTime endTime) {
            this.dayOfWeek = dayOfWeek;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        // Getters y Setters
        public String getDayOfWeek() { return dayOfWeek; }
        public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }
        public LocalTime getStartTime() { return startTime; }
        public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
        public LocalTime getEndTime() { return endTime; }
        public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    }
} 