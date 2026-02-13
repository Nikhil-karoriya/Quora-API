package com.example.QuoraAPI.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.models.Comment;
import com.example.QuoraAPI.repositories.CommentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment addCommentOnAnswer(Comment newComment) {

        return commentRepository.save(newComment);
    }

    public Comment addCommentOnComment(UUID parentCommentId,Comment newComment) {
        
        Comment parentComment= commentRepository.findById(parentCommentId).get();
        
        newComment.setParentComment(parentComment);
        
        return commentRepository.save(newComment);                   
    }
}
