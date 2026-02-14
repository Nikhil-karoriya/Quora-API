package com.example.QuoraAPI.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.dto.UserRequest;
import com.example.QuoraAPI.dto.UserResponse;
import com.example.QuoraAPI.models.BaseModel;
import com.example.QuoraAPI.models.User;
import com.example.QuoraAPI.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse register(UserRequest userRequest) {
        
        User user= User.builder()
                       .username(userRequest.getUsername())
                       .email(userRequest.getEmail())
                       .bio(userRequest.getBio())
                       .build();

        user= userRepository.save(user);

        return createUserResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        
        List<UserResponse> responses= new ArrayList<>();

        List<User> users= userRepository.findAll();

        for(User user: users){
            responses.add(createUserResponse(user));
        }
        
        return responses;
    }

    public UserResponse getUserInfo(UUID userId) {
        
        User user= userRepository.findById(userId).get();

        if(user == null) return new UserResponse();

        return createUserResponse(user);
    }

    public UserResponse updateUser(UUID userId, UserRequest userRequest) {

        Optional<User> userDb= userRepository.findById(userId);

        User user= userDb.get();

        User newUser= User.builder()
                       .username(userRequest.getUsername())
                       .email(userRequest.getEmail())
                       .bio(userRequest.getBio())
                       .build();
        
        if(user == null){
            
            user= userRepository.save(newUser);

            return createUserResponse(user);
        }
        
        if(newUser.getUsername() != null) user.setUsername(newUser.getUsername());
        
        if(newUser.getEmail() != null) user.setEmail(newUser.getEmail());
        
        if(newUser.getBio() != null) user.setBio(newUser.getBio());
        
        user= userRepository.save(newUser);

        return createUserResponse(user);
    }

    private UserResponse createUserResponse(User user){
        
        return UserResponse.builder()
                           .id(user.getId())
                           .username(user.getUsername())
                           .email(user.getEmail())
                           .bio(user.getBio())
                           .followers(mapToUUID(user.getFollowerSet()))
                           .follows(mapToUUID(user.getFollowSet()))
                           .questions(mapToUUID(user.getQuestions()))
                           .answers(mapToUUID(user.getAnswers()))
                           .comments(mapToUUID(user.getComments()))
                           .build();
    }
    
    private <T extends BaseModel> Set<UUID> mapToUUID(Set<T> set){

        Set<UUID> result= new HashSet<>();
        
        for(T ele: set){
            result.add(ele.getId());
        }

        return result;
    }
}
