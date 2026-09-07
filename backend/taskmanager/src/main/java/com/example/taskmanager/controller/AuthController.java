package com.example.taskmanager.controller;

import com.example.taskmanager.dto.LoginRequest;
import com.example.taskmanager.dto.LoginResponse;
import com.example.taskmanager.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,JwtService jwtService){
        this.authenticationManager=authenticationManager;
        this.jwtService=jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token=jwtService.generateToken(
                (org.springframework.security.core.userdetails.UserDetails)
                     authentication.getPrincipal()
        );

        return new LoginResponse(
                "Login successful",
                request.getUsername(),
                token
        );
    }
}
