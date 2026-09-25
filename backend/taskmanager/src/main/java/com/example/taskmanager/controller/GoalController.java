package com.example.taskmanager.controller;

import com.example.taskmanager.dto.GoalRequest;
import com.example.taskmanager.dto.GoalResponse;
import com.example.taskmanager.repository.GoalRepository;
import com.example.taskmanager.service.GoalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goals")
public class GoalController {
    private final GoalService goalService;

    public GoalController(GoalService goalService){
        this.goalService=goalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GoalResponse createGoal(
            @Valid @RequestBody GoalRequest request
            ){
        return goalService.createGoal(request);
    }

    @GetMapping
    public List<GoalResponse> getMygoal(){
        return goalService.getMyGoal();
    }
}
