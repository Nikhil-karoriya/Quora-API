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

import com.example.QuoraAPI.adapters.UserAdapter;
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

    private final UserAdapter userAdapter;
 
    @PostMapping
    public ResponseEntity<UserResponse> registerUser( @RequestBody UserRequest userRequest){
        
        User newUser= userAdapter.toUser(userRequest);
        
        User user= userService.register(newUser);
        
        UserResponse response= userAdapter.toDto(user);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){

        List<User> users= userService.getAllUsers();
        
        List<UserResponse> responses= userAdapter.toDtoAll(users);

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserInfo(@PathVariable("userId") UUID userId){
        
        User user= userService.getUserInfo(userId);
        
        UserResponse response= userAdapter.toDto(user);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);   
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser( @PathVariable("userId") UUID userId,
                                            @RequestBody UserRequest userRequest){

        User newUser= userAdapter.toUser(userRequest);
        
        User user= userService.updateUser(userId, newUser);

        UserResponse response= userAdapter.toDto(user);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);  
    }
}
