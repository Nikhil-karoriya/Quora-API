package com.example.QuoraAPI.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.models.User;
import com.example.QuoraAPI.repositories.UserRepository;

import io.micrometer.common.lang.NonNull;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User register(User user) {

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public User getUserInfo(UUID userId) {
        
        return userRepository.findById(userId).get();
    }

    @Transactional
    public User updateUser(UUID userId, User newUser) {

        User user= userRepository.findById(userId).get();

        if(newUser.getUsername() == null) newUser.setUsername("");
        
        if(newUser.getUsername() != "") user.setUsername(newUser.getUsername());
        
        if(newUser.getEmail() != null) user.setEmail(newUser.getEmail());
        
        if(newUser.getBio() != null) user.setBio(newUser.getBio());
        
        return userRepository.save(user);
    }

}
