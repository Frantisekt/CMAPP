package com.demo.serverless.domain.model;

import java.time.Duration;
import java.util.UUID;

public class Treatment {
    private UUID id;
    private String name;
    private String description;
    private Duration estimatedDuration;
    private double cost;
    private String category;
    private boolean active;
    private String notes;

    public Treatment() {
        this.id = UUID.randomUUID();
        this.active = true;
    }

    public Treatment(String name, String description, Duration estimatedDuration, 
                    double cost, String category, String notes) {
        this();
        this.name = name;
        this.description = description;
        this.estimatedDuration = estimatedDuration;
        this.cost = cost;
        this.category = category;
        this.notes = notes;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Duration getEstimatedDuration() { return estimatedDuration; }
    public void setEstimatedDuration(Duration estimatedDuration) { this.estimatedDuration = estimatedDuration; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
} 