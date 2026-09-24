package com.example.taskmanager.service;


import com.example.taskmanager.mapper.GoalMapper;
import com.example.taskmanager.repository.GoalRepository;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

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


    



}
