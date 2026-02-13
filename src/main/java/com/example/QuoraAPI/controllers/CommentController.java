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

    @PostMapping("/{answerId}/answer")
    public ResponseEntity<Comment> addCommentOnAnswer(@RequestBody Comment newComment){
        
        Comment comment= commentService.addCommentOnAnswer( newComment);

        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }
    
    @PostMapping("/{commentId}/comment")
    public ResponseEntity<Comment> addCommentOnComment(@PathVariable("commentId") UUID commentId,
                                                       @RequestBody Comment newComment){
        
        Comment comment = commentService.addCommentOnComment(commentId, newComment);
    
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

}
