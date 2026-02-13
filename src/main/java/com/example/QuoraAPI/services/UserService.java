package com.example.QuoraAPI.services;

import java.util.List;
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

    public User register(User user) {
        
        return userRepository.save(user);                  
    }

    public User getUserInfo(UUID userId) {
        
        return userRepository.findById(userId).get();
    }

    public User updateUser(UUID userId, User newUser) {

        Optional<User> userDb= userRepository.findById(userId);

        User user= userDb.get();
        
        if(user == null){
            return userRepository.save(newUser);
        }
        
        if(newUser.getUsername() != null) user.setUsername(newUser.getUsername());
        
        if(newUser.getEmail() != null) user.setEmail(newUser.getEmail());
        
        if(newUser.getBio() != null) user.setBio(newUser.getBio());
        
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        
        return userRepository.findAll();
    }
    
}
