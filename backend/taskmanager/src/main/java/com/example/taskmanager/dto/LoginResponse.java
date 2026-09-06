package com.example.taskmanager.dto;

public class LoginResponse {
    private String username;
    private String message;

    public LoginResponse(String username, String message){
        this.message=message;
        this.username=message;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
}
