package com.example.QuoraAPI.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.models.User;
import com.example.QuoraAPI.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(String username, String email, String bio) {

        User newUser= User.builder()
                          .username(username)
                          .email(email)
                          .bio(bio)
                          .build();

        return userRepository.save(newUser);                  
    }

    public User getUserInfo(UUID userId) {
        
        return userRepository.findById(userId).get();
    }

    public User updateUser(UUID userId, String username, String email, String bio) {

        Optional<User> userDb= userRepository.findById(userId);

        User user= userDb.get();
        
        if(user == null){
            
            User newUser= User.builder()
                          .username(username)
                          .email(email)
                          .bio(bio)
                          .build();

            return userRepository.save(newUser);
        }
        
        if(username != null) user.setUsername(username);
        
        if(user.getEmail() != null) user.setEmail(email);
        
        if(user.getBio() != null) user.setBio(bio);
        
        return userRepository.save(user);
    }
    
}
