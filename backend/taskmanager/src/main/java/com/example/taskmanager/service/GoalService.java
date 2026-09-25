package com.example.taskmanager.service;


import com.example.taskmanager.dto.GoalRequest;
import com.example.taskmanager.dto.GoalResponse;
import com.example.taskmanager.mapper.GoalMapper;
import com.example.taskmanager.model.Goal;
import com.example.taskmanager.model.GoalStatus;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.GoalRepository;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GoalService {
    private final GoalRepository goalRepository;
    private final UserRepository userRepository;
    private final GoalMapper goalMapper;


    public GoalService(GoalRepository goalRepository, UserRepository userRepository ,GoalMapper goalMapper){
        this.goalRepository=goalRepository;
        this.userRepository=userRepository;
        this.goalMapper=goalMapper;
    }


    
    private User getCurrentUser(){
        Authentication authentication=
                SecurityContextHolder
                        .getContext().getAuthentication();

        String username= authentication.getName();

        return userRepository.findByName(username)
                .orElseThrow(()->
                        new RuntimeException("No User Found"));
    }

    public GoalResponse createGoal(
            GoalRequest request
    ){

        User user= getCurrentUser();

        Goal goal= new Goal();

        goal.setTitle(request.getTitle());
        goal.setDescription(request.getDescription());
        goal.setStartDate(request.getStartDate());
        goal.setEndDate(request.getEndDate());

        goal.setStatus(GoalStatus.ACTIVE);

        goal.setCreatedAt(LocalDateTime.now());
        goal.setUpdatedAt(LocalDateTime.now());

        goal.setUser(user);

        Goal savedGoal= goalRepository.save(goal);

        return goalMapper.toResponse(savedGoal);
    }

    public List<GoalResponse> getMyGoal(){
        User user= getCurrentUser();
        return goalRepository
                .findByUserId(user.getId())
                .stream()
                .map(goalMapper::toResponse)
                .toList();
    }
}
