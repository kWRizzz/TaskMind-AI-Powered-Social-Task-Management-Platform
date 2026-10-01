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

        Long progress = 0l;

        if (totalTasks > 0) {
            progress =
                    ((Long) completedTasks / totalTasks) * 100;
        }

        return new GoalResponse(
                progress,
                completedTasks,
                totalTasks,
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
