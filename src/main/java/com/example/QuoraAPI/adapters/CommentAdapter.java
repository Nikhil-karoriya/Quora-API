package com.example.QuoraAPI.adapters;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.QuoraAPI.dto.CommentRequest;
import com.example.QuoraAPI.dto.CommentResponse;
import com.example.QuoraAPI.models.Comment;
import com.example.QuoraAPI.repositories.AnswerRepository;
import com.example.QuoraAPI.repositories.CommentRepository;
import com.example.QuoraAPI.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CommentAdapter {
    
    private final UserRepository userRepository;

    private final AnswerRepository answerRepository;

    private final CommentRepository commentRepository;

    public Comment toCommentOnAnswer(CommentRequest commentRequest){

        return Comment.builder()
                      .user(userRepository.findById(commentRequest.getUserId()).get())
                      .answer(answerRepository.findById(commentRequest.getAnswerId()).get())
                      .text(commentRequest.getText())
                      .build();
    }

    public Comment toCommentOnComment(CommentRequest commentRequest){
        
        return Comment.builder()
                      .user(userRepository.findById(commentRequest.getUserId()).get())
                      .answer(answerRepository.findById(commentRequest.getAnswerId()).get())
                      .text(commentRequest.getText())
                      .parentComment(commentRepository.findById(commentRequest.getParentCommentId()).get())
                      .build();
    }

    public CommentResponse toDto(Comment comment){

         return CommentResponse.builder()
                              .commentId(comment.getId())
                              .answer(comment.getAnswer().getText())
                              .user(comment.getUser().getUsername())
                              .text(comment.getText())
                              .parentComment(comment.getParentComment() == null ? 
                                            "" : comment.getParentComment().getText())

                              .childComments(Optional.ofNullable(comment.getChildComments())
                                               .orElse(Collections.emptySet())
                                               .stream()
                                               .map(Comment::getProperComment)
                                               .collect(Collectors.toList()))
                              .build();
    }
}
