package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskResponse;
import com.example.taskmanager.mapper.TaskMapper;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServices (UserRepository userRepository ,TaskRepository taskRepository,TaskMapper taskMapper, PasswordEncoder passwordEncoder)  {
        this.userRepository=userRepository;
        this.taskRepository=taskRepository;
        this.taskMapper=taskMapper;
        this.passwordEncoder=passwordEncoder;
    }


    public User createUser(User user){

        String encodedPassword= passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        if(user.getRole()==null || user.getRole().isBlank()){
            user.setRole("USER");
        }

        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public List<TaskResponse> getUserTasks(Long userId){
        return taskRepository.findByUserId(userId)
                .stream()
                .map(taskMapper::toResponse)
                .toList();
    }
}
