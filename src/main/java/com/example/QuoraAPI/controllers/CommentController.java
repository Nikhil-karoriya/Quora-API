package com.example.QuoraAPI.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuoraAPI.models.Comment;
import com.example.QuoraAPI.services.CommentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{answerId}/answers")
    public ResponseEntity<Comment> addCommentOnAnswer( @PathVariable("answerId") UUID answerId, 
                                                       @RequestBody UUID userId, 
                                                       @RequestBody String text){
        
        Comment comment= commentService.addCommentOnAnswer(answerId, userId, text);

        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }
    
    @PostMapping("/{commentId}/comments")
    public ResponseEntity<Comment> addCommentOnComment( @PathVariable("commentId") UUID commentId,
                                                        @RequestBody UUID userId, 
                                                        @RequestBody String text){
        
        Comment comment = commentService.addCommentOnComment(commentId, userId, text);
    
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

}
