package com.example.QuoraAPI.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuoraAPI.dto.UserRequest;
import com.example.QuoraAPI.dto.UserResponse;
import com.example.QuoraAPI.models.User;
import com.example.QuoraAPI.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
 
    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest user){
        
        UserResponse response= userService.register(user);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){

        List<UserResponse> response= userService.getAllUsers();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserInfo(@PathVariable("userId") UUID userId){
        
        UserResponse response= userService.getUserInfo(userId);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);   
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser( @PathVariable("userId") UUID userId,
                                            @RequestBody UserRequest newUser){

        UserResponse response= userService.updateUser(userId, newUser);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);  
    }
}
