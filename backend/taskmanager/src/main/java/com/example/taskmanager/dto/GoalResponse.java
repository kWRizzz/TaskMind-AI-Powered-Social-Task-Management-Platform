package com.example.taskmanager.dto;

import com.example.taskmanager.model.GoalStatus;

import java.time.LocalDateTime;

public class GoalResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private GoalStatus status;

    public GoalResponse(Long id, String title, String description, LocalDateTime startDate, LocalDateTime endDate, GoalStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public GoalStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


}
