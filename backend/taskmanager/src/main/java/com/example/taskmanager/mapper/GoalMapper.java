package com.example.taskmanager.mapper;

import com.example.taskmanager.dto.GoalResponse;
import com.example.taskmanager.model.Goal;
import org.springframework.stereotype.Component;

@Component
public class GoalMapper {
    public GoalResponse toResponse(Goal goal){
        return new GoalResponse(
                goal.getId(),
                goal.getTitle(),
                goal.getDescription(),
                goal.getStartDate(),
                goal.getEndDate(),
                goal.getStatus(),
                goal.getCreatedAt(),
                goal.getUpdatedAt()

        );
    }
}
