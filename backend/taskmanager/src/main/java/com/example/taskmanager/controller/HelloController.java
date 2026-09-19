package com.example.taskmanager.controller;


import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.dto.TaskResponse;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskPriority;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.service.TaskServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class HelloController {

    private final TaskServices taskServices;

    public HelloController(TaskServices taskServices){
        this.taskServices=taskServices;
    }

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name){
        return "hello " + name;
    }
    @GetMapping("/greet")
    public String greet(@RequestParam String name){
        return "params-->" + name;
    }

//   post APIs

// test inComplete POSTMAN STATUS == TRUE

    @PostMapping("/task")
    public TaskResponse createTask(@Valid @RequestBody TaskRequest request){
        return taskServices.createTask(request);
    }

    @GetMapping("/task")
    public List<TaskResponse> getAllTask(){
        return taskServices.getAllTask();

    }


    @GetMapping("/task/{id}")
    public TaskResponse getTaskById(@PathVariable Long id){
        return  taskServices.gerTaskById(id);

//        Task task= taskR

    }

    @PutMapping("/task/{id}")
    public TaskResponse updateTask(@PathVariable Long id,@Valid @RequestBody TaskRequest request){
        return taskServices.updateTask(id,request);
    }


    @DeleteMapping("/task/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteTask(@PathVariable Long id){
          taskServices.deleteTask(id);
          return "Task delete";

    }

    @GetMapping
    public List<TaskResponse> getTask(
            @RequestParam(required = false) String search,
            @RequestParam(required = false)TaskStatus status,
            @RequestParam(required = false)TaskPriority priority
            ){
        return taskServices.searchTasks(
                search, status, priority);
    }

}