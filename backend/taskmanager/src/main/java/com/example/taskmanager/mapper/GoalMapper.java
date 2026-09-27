package com.example.taskmanager.mapper;

import com.example.taskmanager.dto.GoalResponse;
import com.example.taskmanager.model.Goal;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Component;

@Component
public class GoalMapper {
    private final TaskRepository taskRepository;

    public GoalMapper(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }

    public GoalResponse toResponse(Goal goal){

        long totalTasks= taskRepository.countByGoalId(
                goal.getId()
        );
        long completedTasks= taskRepository.countByGoalIdAndStatus(
                goal.getId(),
                TaskStatus.COMPLETED
        );

        double progress = 0;

        if (totalTasks > 0) {
            progress =
                    ((double) completedTasks / totalTasks) * 100;
        }

        return new GoalResponse(
                goal.getId(),
                goal.getTitle(),
                goal.getDescription(),
                goal.getStartDate(),
                goal.getEndDate(),
                goal.getStatus(),
                goal.getCreatedAt(),
                goal.getUpdatedAt(),
                totalTasks,
                completedTasks,
                progress

        );
    }
}
