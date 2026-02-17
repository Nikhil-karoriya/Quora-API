package com.example.QuoraAPI.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.models.Follow;
import com.example.QuoraAPI.models.User;
import com.example.QuoraAPI.repositories.FollowRepository;
import com.example.QuoraAPI.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    private final UserRepository userRepository;

    @Transactional
    public void addFollower(UUID userId, UUID targetUserId) {
        
        User follower= userRepository.findById(userId).get();
        
        User followee= userRepository.findById(targetUserId).get();
        
        Follow newFollow= followRepository.findByFollowerAndFollowee(follower, followee);
        
        if(newFollow == null){
            
            newFollow= Follow.builder()
                         .follower(follower)
                         .followee(followee)
                         .build();
            
            followRepository.save(newFollow);                        
        }
            
    }
    
}
