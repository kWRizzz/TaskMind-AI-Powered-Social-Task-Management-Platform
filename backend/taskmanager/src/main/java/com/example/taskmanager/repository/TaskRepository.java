package com.example.taskmanager.repository;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskPriority;
import com.example.taskmanager.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByUserId(Long userId);

    List<Task> findByUserIdAndTitleContainingIgnoreCase(
            Long userId,
            String title
    );
    List<Task> findByUserIdAndStatus(
            Long userId,
            TaskStatus status
    );
    List<Task> findByUserIdAndPriority(
            Long userId,
            TaskPriority priority
    );

}
