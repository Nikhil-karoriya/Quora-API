package com.example.QuoraAPI.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.models.Follow;
import com.example.QuoraAPI.repositories.FollowRepository;
import com.example.QuoraAPI.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    private final UserRepository userRepository;

    public void addFollower(UUID userId, UUID targetUserId) {
        
        Follow newFollow= Follow.builder()
                                .follower(userRepository.findById(userId).get())
                                .followee(userRepository.findById(targetUserId).get())
                                .build();
            
        followRepository.save(newFollow);                        
    }
    
}
