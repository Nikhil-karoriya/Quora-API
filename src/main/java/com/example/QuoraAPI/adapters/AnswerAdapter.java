package com.example.QuoraAPI.adapters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.QuoraAPI.dto.AnswerRequest;
import com.example.QuoraAPI.dto.AnswerResponse;
import com.example.QuoraAPI.models.Answer;
import com.example.QuoraAPI.models.Comment;
import com.example.QuoraAPI.models.LikeAnswer;
import com.example.QuoraAPI.models.User;
import com.example.QuoraAPI.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AnswerAdapter {
    
    private final UserRepository userRepository;

    public Answer toAnswer(AnswerRequest answerRequest){
        
        return Answer.builder()
                     .text(answerRequest.getText())
                     .user(userRepository.findById(answerRequest.getUserId()).get())
                     .build();
    }

    public AnswerResponse toDto(Answer answer){
        
        return AnswerResponse.builder()
                             .text(answer.getText())
                             .user(answer.getUser().getUsername())
                             .question(answer.getQuestion().getTitle())
                             .answerId(answer.getId())

                             .comments(Optional.ofNullable(answer.getComments())
                                               .orElse(Collections.emptySet())
                                               .stream()
                                               .map(Comment::getProperComment)
                                               .collect(Collectors.toList()))

                            .likes(Optional.ofNullable(answer.getLikes())
                                            .orElse(Collections.emptySet())
                                            .stream()
                                            .map(LikeAnswer::getUser)
                                            .map(User::getUsername)
                                            .collect(Collectors.toList()))
                             .build();
    }

    public List<AnswerResponse> toDtoAll(List<Answer> answers){

        List<AnswerResponse> responses= new ArrayList<>();

        for(Answer answer: answers){
            responses.add(toDto(answer));
        }
        
        return responses;
    }
}