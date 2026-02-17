package com.example.QuoraAPI.adapters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.QuoraAPI.dto.UserRequest;
import com.example.QuoraAPI.dto.UserResponse;
import com.example.QuoraAPI.models.Answer;
import com.example.QuoraAPI.models.Comment;
import com.example.QuoraAPI.models.Follow;
import com.example.QuoraAPI.models.Question;
import com.example.QuoraAPI.models.User;

@Component
public class UserAdapter {
    
    public User toUser(UserRequest userRequest){

        return User.builder()
                   .username(userRequest.getUsername())
                   .email(userRequest.getEmail())
                   .bio(userRequest.getBio())
                   .build();
    }

    public UserResponse toDto(User user){
        
        return UserResponse.builder()
                           .id(user.getId())
                           .username(user.getUsername())
                           .email(user.getEmail())
                           .bio(user.getBio())
                           .followers(Optional.ofNullable(user.getFollowers())
                                              .orElse(Collections.emptySet())
                                              .stream()
                                              .map(Follow::getFollower)
                                              .map(User::getUsername)
                                              .collect(Collectors.toSet()))

                           .follows(Optional.ofNullable(user.getFollowing())
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

    public List<UserResponse> toDtoAll(List<User> users){

        List<UserResponse> responses= new ArrayList<>();

        for(User user: users){
            responses.add(toDto(user));
        }
        
        return responses;
    }
}
