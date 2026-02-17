package com.example.QuoraAPI.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.models.Comment;
import com.example.QuoraAPI.repositories.CommentRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Comment addCommentOnAnswer(UUID answerId, Comment newComment) {

        return commentRepository.save(newComment);
    }

    @Transactional
    public Comment addCommentOnComment(UUID parentCommentId, Comment newComment) {
        
        return commentRepository.save(newComment);
    }

    public Comment getCommentByID(UUID commentId) {
        
        return commentRepository.findById(commentId).get();
    }
}
