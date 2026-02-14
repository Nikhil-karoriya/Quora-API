package com.example.QuoraAPI.services;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.dto.AnswerResponse;
import com.example.QuoraAPI.dto.CommentRequest;
import com.example.QuoraAPI.dto.CommentResponse;
import com.example.QuoraAPI.models.Comment;
import com.example.QuoraAPI.repositories.AnswerRepository;
import com.example.QuoraAPI.repositories.CommentRepository;
import com.example.QuoraAPI.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final AnswerRepository answerRepository;

    private final UserRepository userRepository;

    public CommentResponse addCommentOnAnswer(CommentRequest newComment) {

        Comment comment = Comment.builder()
                                 .answer(answerRepository.findById(newComment.getAnswerId()).get())
                                 .user(userRepository.findById(newComment.getUserId()).get())
                                 .text(newComment.getText())
                                 .build();

        comment= commentRepository.save(comment);

        return CommentResponse.builder()
                              .commentId(comment.getId())
                              .answerId(comment.getAnswer().getId())
                              .userId(comment.getUser().getId())
                              .text(comment.getText())
                              .build();
    }

    public CommentResponse addCommentOnComment(UUID parentCommentId,CommentRequest newComment) {
        
        Comment parentComment;

        try{
            parentComment= commentRepository.findById(parentCommentId).get();
        }
        catch(NoSuchElementException ne){
            return CommentResponse.builder().build();
        }
        
        Comment comment = Comment.builder()
                                 .answer(answerRepository.findById(newComment.getAnswerId()).get())
                                 .user(userRepository.findById(newComment.getUserId()).get())
                                 .text(newComment.getText())
                                 .parentComment(commentRepository.findById(parentCommentId).get())
                                 .build();
        
        commentRepository.save(comment);

        return CommentResponse.builder()
                              .commentId(comment.getId())
                              .answerId(comment.getAnswer().getId())
                              .userId(comment.getUser().getId())
                              .text(comment.getText())
                              .parentCommentId(parentCommentId)
                              .build();
    }
}
