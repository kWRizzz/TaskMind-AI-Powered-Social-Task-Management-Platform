package com.example.taskmanager.dto;

public class LoginResponse {
    private String username;
    private String message;
    private String token;

    public LoginResponse(String username, String message ,String token){
        this.message=message;
        this.username=username;
        this.token=token;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
}
