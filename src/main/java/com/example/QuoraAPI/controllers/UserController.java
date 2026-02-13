package com.example.QuoraAPI.controllers;

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

import com.example.QuoraAPI.models.User;
import com.example.QuoraAPI.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
 
    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User newUser){
        
        User user= userService.register(newUser);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserInfo(@PathVariable("userId") UUID userId){
        
        User user= userService.getUserInfo(userId);
        
        return ResponseEntity.status(HttpStatus.OK).body(user);   
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") UUID userId){

        User user= userService.updateUser(userId);
        
        return ResponseEntity.status(HttpStatus.OK).body(user);  
    }

    @PostMapping("/{userId}/follow/{targetUserId}")
    public ResponseEntity<?> followUser(@PathVariable("userId") UUID userId,
                                        @PathVariable("targetUserId") UUID targetUserId){
        
        userService.addFollower(userId, targetUserId);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
