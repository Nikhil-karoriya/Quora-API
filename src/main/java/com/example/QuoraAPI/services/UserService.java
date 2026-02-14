package com.example.QuoraAPI.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.dto.UserRequest;
import com.example.QuoraAPI.dto.UserResponse;
import com.example.QuoraAPI.models.Answer;
import com.example.QuoraAPI.models.BaseModel;
import com.example.QuoraAPI.models.Comment;
import com.example.QuoraAPI.models.Follow;
import com.example.QuoraAPI.models.Question;
import com.example.QuoraAPI.models.User;
import com.example.QuoraAPI.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
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
        
        User user;

        try{
            user= userRepository.findById(userId).get();
        }
        catch(NoSuchElementException ne){
            return new UserResponse();   
        }

        return createUserResponse(user);
    }

    @Transactional
    public UserResponse updateUser(UUID userId, UserRequest userRequest) {

        Optional<User> userDb= userRepository.findById(userId);

        User user;

        if(userRequest.getUsername() == null) userRequest.setUsername("");

        User newUser= User.builder()
                       .username(userRequest.getUsername())
                       .email(userRequest.getEmail())
                       .bio(userRequest.getBio())
                       .build();

        try{
            user = userDb.get();
        }
        catch(NoSuchElementException ne){

            user= userRepository.save(newUser);

            return createUserResponse(user);
        }
        
        if(newUser.getUsername() != "") user.setUsername(newUser.getUsername());
        
        if(newUser.getEmail() != null) user.setEmail(newUser.getEmail());
        
        if(newUser.getBio() != null) user.setBio(newUser.getBio());
        
        user= userRepository.save(user);

        return createUserResponse(user);
    }

    private UserResponse createUserResponse(User user){
        
        return UserResponse.builder()
                           .id(user.getId())
                           .username(user.getUsername())
                           .email(user.getEmail())
                           .bio(user.getBio())
                           .followers(Optional.ofNullable(user.getFollowerSet())
                                              .orElse(Collections.emptySet())
                                              .stream()
                                              .map(Follow::getFollower)
                                              .map(User::getUsername)
                                              .collect(Collectors.toSet()))

                           .follows(Optional.ofNullable(user.getFollowSet())
                                              .orElse(Collections.emptySet())
                                              .stream()
                                              .map(Follow::getFollowee)
                                              .map(User::getUsername)
                                              .collect(Collectors.toSet()))

                           .questions(Optional.ofNullable(user.getQuestions())
                                              .orElse(Collections.emptySet())
                                              .stream()
                                              .map(Question::getTitle)
                                              .collect(Collectors.toSet()))

                           .answers(Optional.ofNullable(user.getAnswers())
                                              .orElse(Collections.emptySet())
                                              .stream()
                                              .map(Answer::getText)
                                              .collect(Collectors.toSet()))

                           .comments(Optional.ofNullable(user.getComments())
                                              .orElse(Collections.emptySet())
                                              .stream()
                                              .map(Comment::getText)
                                              .collect(Collectors.toSet()))
                           .build();
    }

}
