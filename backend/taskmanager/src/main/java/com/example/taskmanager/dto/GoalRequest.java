package com.example.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class GoalRequest {
    @NotBlank(message = "Enter title ")
    @Size(
            min = 0,
            max = 200,
            message = "goal mus be under 200 words"
    )
    private String title;

    @Size(
            max = 1000,
            message ="description must be of 1000 word limited"
    )
    private String description;

    private LocalDateTime startDate;
    private LocalDateTime endDate;


    public GoalRequest(
            String title,
            String description,
            LocalDateTime startDate,
            LocalDateTime endDate
    ){
        this.title= title;
        this.description=description;
        this.startDate=startDate;
        this.endDate=endDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
