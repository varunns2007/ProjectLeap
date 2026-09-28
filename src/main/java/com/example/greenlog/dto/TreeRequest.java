package com.example.greenlog.dto;

import com.example.greenlog.enums.SurvivalStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class TreeRequest {

    @NotBlank(message = "Species name is required")
    private String species;

    private String location;

    private LocalDate datePlanted;

    @NotNull(message = "Survival status is required")
    private SurvivalStatus status;

    private Long volunteerId;

    private Long plantationDriveId;

    public TreeRequest() {}

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDate getDatePlanted() { return datePlanted; }
    public void setDatePlanted(LocalDate datePlanted) { this.datePlanted = datePlanted; }

    public SurvivalStatus getStatus() { return status; }
    public void setStatus(SurvivalStatus status) { this.status = status; }

    public Long getVolunteerId() { return volunteerId; }
    public void setVolunteerId(Long volunteerId) { this.volunteerId = volunteerId; }

    public Long getPlantationDriveId() { return plantationDriveId; }
    public void setPlantationDriveId(Long plantationDriveId) { this.plantationDriveId = plantationDriveId; }
}