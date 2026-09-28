package com.example.greenlog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class PlantationDriveRequest {

    @NotBlank(message = "Drive name cannot be empty")
    private String name;

    @NotBlank(message = "Location cannot be empty")
    private String location;

    @NotNull(message = "Date is required")
    private LocalDate date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}