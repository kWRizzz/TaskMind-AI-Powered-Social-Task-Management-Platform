package com.example.taskmanager.repository;

import com.example.taskmanager.model.Goal;
import com.example.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal,Long> {
    List<Goal> findByUserId(Long userId);
}
