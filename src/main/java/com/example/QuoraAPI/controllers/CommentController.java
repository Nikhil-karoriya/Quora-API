package com.example.QuoraAPI.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuoraAPI.adapters.CommentAdapter;
import com.example.QuoraAPI.dto.CommentRequest;
import com.example.QuoraAPI.dto.CommentResponse;
import com.example.QuoraAPI.models.Comment;
import com.example.QuoraAPI.services.CommentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final CommentAdapter commentAdapter;

    @PostMapping("/{answerId}/answer")
    public ResponseEntity<CommentResponse> addCommentOnAnswer(@PathVariable("answerId") UUID answerId,
                                                             @RequestBody CommentRequest commentRequest){
        
        Comment newComment= commentAdapter.toCommentOnAnswer(commentRequest);                                                        

        Comment comment= commentService.addCommentOnAnswer(answerId, newComment);

        CommentResponse response= commentAdapter.toDto(comment);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PostMapping("/{commentId}/comment")
    public ResponseEntity<CommentResponse> addCommentOnComment(@PathVariable("commentId") UUID commentId,
                                                       @RequestBody CommentRequest commentRequest){
        
        Comment newComment= commentAdapter.toCommentOnComment(commentRequest);

        Comment comment = commentService.addCommentOnComment(commentId, newComment);

        CommentResponse response= commentAdapter.toDto(comment);
    
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponse> getCommentByID(@PathVariable("commentId") UUID commentId){

        Comment comment= commentService.getCommentByID(commentId);

        CommentResponse response= commentAdapter.toDto(comment);
    
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
