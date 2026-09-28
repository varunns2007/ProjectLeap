package com.example.greenlog.dto;

import com.example.greenlog.enums.SurvivalStatus;
import jakarta.validation.constraints.NotNull;

public class CheckInRequest {

    @NotNull(message = "Tree ID is required")
    private Long treeId;

    @NotNull(message = "Survival status is required")
    private SurvivalStatus status;

    private Long volunteerId;

    public CheckInRequest() {}

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public SurvivalStatus getStatus() {
        return status;
    }

    public void setStatus(SurvivalStatus status) {
        this.status = status;
    }

    public Long getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Long volunteerId) {
        this.volunteerId = volunteerId;
    }
}