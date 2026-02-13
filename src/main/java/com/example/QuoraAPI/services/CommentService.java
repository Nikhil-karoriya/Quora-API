package com.example.QuoraAPI.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.models.Answer;
import com.example.QuoraAPI.models.Comment;
import com.example.QuoraAPI.models.User;
import com.example.QuoraAPI.repositories.AnswerRepository;
import com.example.QuoraAPI.repositories.CommentRepository;
import com.example.QuoraAPI.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final AnswerRepository answerRepository;

    public Comment addCommentOnAnswer(UUID answerId, UUID userId, String text) {
        
        Answer answer = answerRepository.findById(answerId).get();

        User user= userRepository.findById(userId).get();
        
        Comment newComment= Comment.builder()
                                   .answer(answer)
                                   .user(user)
                                   .text(text)
                                   .build();

        return commentRepository.save(newComment);
    }

    public Comment addCommentOnComment(UUID commentId, UUID userId, String text) {
        
        Comment parentComment= commentRepository.findById(commentId).get();

        User user= userRepository.findById(userId).get();

        Answer answer= parentComment.getAnswer();

        Comment newComment= Comment.builder()
                                   .answer(answer)
                                   .user(user)
                                   .text(text)
                                   .parentComment(parentComment)
                                   .build(); 

        return commentRepository.save(newComment);                   
    }
}
