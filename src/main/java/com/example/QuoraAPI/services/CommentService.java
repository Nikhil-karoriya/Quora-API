package com.example.QuoraAPI.services;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.dto.CommentRequest;
import com.example.QuoraAPI.dto.CommentResponse;
import com.example.QuoraAPI.models.Answer;
import com.example.QuoraAPI.models.Comment;
import com.example.QuoraAPI.models.User;
import com.example.QuoraAPI.repositories.AnswerRepository;
import com.example.QuoraAPI.repositories.CommentRepository;
import com.example.QuoraAPI.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final AnswerRepository answerRepository;

    private final UserRepository userRepository;

    @Transactional
    public CommentResponse addCommentOnAnswer(UUID answerId, CommentRequest newComment) {

        newComment.setAnswerId(answerId);

        Answer answer;
        
        User user;

        try{
            answer= answerRepository.findById(newComment.getAnswerId()).get();

            user= userRepository.findById(newComment.getUserId()).get();
        }
        catch(NoSuchElementException ne){
            return CommentResponse.builder().build();
        }

        Comment comment = Comment.builder()
                                 .answer(answer)
                                 .user(user)
                                 .text(newComment.getText())
                                 .build();

        comment= commentRepository.save(comment);

        return createCommentResponse(comment);
    }

    @Transactional
    public CommentResponse addCommentOnComment(UUID parentCommentId, CommentRequest newComment) {
        
        newComment.setParentCommentId(parentCommentId);

        Comment parentComment;

        Answer answer;
        
        User user;

        try{

            parentComment= commentRepository.findById(parentCommentId).get();
            
            answer= answerRepository.findById(newComment.getAnswerId()).get();

            user= userRepository.findById(newComment.getUserId()).get();
        }
        catch(NoSuchElementException ne){
            
            return CommentResponse.builder().build();
        }
        
        Comment comment = Comment.builder()
                                 .answer(answer)
                                 .user(user)
                                 .text(newComment.getText())
                                 .parentComment(parentComment)
                                 .build();
        
        commentRepository.save(comment);

        return createCommentResponse(comment);
    }

    private CommentResponse createCommentResponse(Comment comment){

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

    public CommentResponse getCommentByID(UUID commentId) {

        Comment comment;
        
        try{
            comment= commentRepository.findById(commentId).get();
        }
        catch(NoSuchElementException ne){
            
            return CommentResponse.builder().build();
        }

        return createCommentResponse(comment);
    }
}
