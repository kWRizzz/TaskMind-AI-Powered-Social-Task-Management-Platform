package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.dto.TaskResponse;
import com.example.taskmanager.exception.TaskNotFoundException;
import com.example.taskmanager.mapper.TaskMapper;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskPriority;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServices {

//  Future dataBase inclusion pending
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

    public TaskServices(TaskRepository taskRepository , TaskMapper taskMapper, UserRepository userRepository){
        this.taskRepository=taskRepository;
        this.taskMapper=taskMapper;
        this.userRepository=userRepository;
    }

//    @POST creating task
    public TaskResponse createTask(TaskRequest request){

//        return taskRepository.save(task);

        Authentication authentication= SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username= authentication.getName();

        User user= userRepository.findByName(username)
                .orElseThrow(()->
                            new RuntimeException("User not found")
                        );


        Task task =new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());

        if(request.getStatus()!=null){
            task.setTaskStatus(
                    TaskStatus.valueOf(request.getStatus().toUpperCase())
            );
        }else {
            task.setTaskStatus(TaskStatus.TODO);
        }

        if(request.getPriority()!=null){
            task.setTaskPriority(
                    TaskPriority.valueOf(request.getPriority().toUpperCase())
            );
        }else{
            task.setTaskPriority(TaskPriority.MEDIUM);
        }

        task.setCategory(request.getCategory());
        task.setDueDate(request.getDueDate());
        task.setEstimatedMinutes(request.getEstimatedMinutes());

        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());


        task.setUser(user);

        Task savedTask= taskRepository.save(task);

        return  taskMapper.toResponse(savedTask);
    }

    public List<TaskResponse> getAllTask(){
        Authentication authentication= SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username= authentication.getName();

        User user= userRepository.findByName(username)
                .orElseThrow(()->
                        new RuntimeException("User not found")
                );



        return taskRepository.findByUserId(user.getId())
                .stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    public TaskResponse gerTaskById(Long id){
        User user= getCurrentUser();

        Task task =taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException("Task Not Found" + id));

        if(!task.getUser().getId().equals(user.getId())){
            throw new RuntimeException("nahi hai koi user login kar ya register kar ");
        }
        return taskMapper.toResponse(task);
    }

    public TaskResponse updateTask(Long id , Task updatedTask , TaskRequest request){

        User user= getCurrentUser();


        Task existingTask= taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException("Cannot delete it" + id ));

        if(!existingTask.getUser().getId().equals(user.getId())){
            throw new RuntimeException("Acces denied");
        }
        existingTask.setTitle(request.getTitle());
        existingTask.setDescription(request.getDescription());

        if(request.getPriority()!=null){
            existingTask.setTaskPriority(TaskPriority.valueOf(request.getPriority().toUpperCase()));
        }
        if (request.getStatus()!=null){
            existingTask.setTaskStatus(
                    TaskStatus.valueOf(request.getStatus().toUpperCase())
            );
        }

        existingTask.setDueDate(request.getDueDate());
        existingTask.setCategory(request.getCategory());
        existingTask.setEstimatedMinutes(request.getEstimatedMinutes());


        existingTask.setUpdatedAt(LocalDateTime.now());

//        existingTask.setCompleted(updatedTask.isCompleted());

        Task updatedTasks=taskRepository.save(existingTask);

//        return taskRepository.save(existingTask);
        return  taskMapper.toResponse(updatedTasks);
        }

    public void deleteTask(Long id){
        User user= getCurrentUser();
        Task task = taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task Not FOund" + id));
        if(!task.getUser().getId().equals(user.getId())){
            throw new RuntimeException("Acces denied");
        }
        taskRepository.delete(task);
    }

    private User getCurrentUser(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userRepository.findByName(username)
                .orElseThrow(()->new RuntimeException("No user found"));
    }

    public List<TaskResponse> getTasksByUser(Long userId){
        return taskRepository.findByUserId(userId)
                .stream()
                .map(taskMapper::toResponse)
                .toList();
    }

}
