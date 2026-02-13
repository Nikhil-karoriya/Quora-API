package com.example.QuoraAPI.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuoraAPI.services.FollowService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/follow")
@AllArgsConstructor
public class FollowController {

    private final FollowService followService;
    
    @PostMapping("/{userId}/follows/{targetUserId}")
    public ResponseEntity<?> followUser(@PathVariable(value= "userId", required= true) UUID userId,
                                        @PathVariable(value= "targetUserId", required= true) UUID targetUserId){
        
        followService.addFollower(userId, targetUserId);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
    
}
