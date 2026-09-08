package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.dto.TaskResponse;
import com.example.taskmanager.exception.TaskNotFoundException;
import com.example.taskmanager.mapper.TaskMapper;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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


        Task task =new Task(
                request.getTitle(),
                request.isCompleted()
        );

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
        Task task =taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException("Task Not Found" + id));
        return taskMapper.toResponse(task);
    }

    public TaskResponse updateTask(Long id , Task updatedTask){
        Task existingTask= taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException("Cannot delete it" + id ));

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setCompleted(updatedTask.isCompleted());

        Task updatedTasks=taskRepository.save(existingTask);

//        return taskRepository.save(existingTask);
        return  taskMapper.toResponse(updatedTasks);
        }

    public void deleteTask(Long id){
        Task task = taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task Not FOund" + id));
        taskRepository.delete(task);
    }


    public List<TaskResponse> getTasksByUser(Long userId){
        return taskRepository.findByUserId(userId)
                .stream()
                .map(taskMapper::toResponse)
                .toList();
    }

}
