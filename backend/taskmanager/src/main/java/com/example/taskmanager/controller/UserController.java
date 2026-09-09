package com.example.taskmanager.controller;


import com.example.taskmanager.dto.TaskResponse;
import com.example.taskmanager.model.User;
import com.example.taskmanager.service.TaskServices;
import com.example.taskmanager.service.UserServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServices userServices;
    private final TaskServices taskServices;

    public UserController(UserServices userServices ,TaskServices taskServices){
        this.userServices=userServices;
        this.taskServices=taskServices;
    }

//    creating a user

    @PostMapping
    public User createUser(@RequestBody User user){
        return userServices.createUser(user);
    }

    @GetMapping
    public List<User> getAllUser(){
        return userServices.getAllUser();
    }

    @GetMapping("/{userId}/tasks")
    public List<TaskResponse> getUserTasks(@PathVariable Long userId){
        return userServices.getUserTasks(userId);
    }
    @GetMapping
    public List<TaskResponse> getMyTask(){
        return taskServices.getAllTask();
    }
//    dummy admin route
    @GetMapping("/admin")
    public String adminOnly(){
        return "Welcome admin";
    }
}
