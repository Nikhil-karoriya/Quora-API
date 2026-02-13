package com.example.QuoraAPI.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuoraAPI.models.Answer;
import com.example.QuoraAPI.models.Comment;
import com.example.QuoraAPI.services.AnswerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/answers")
@AllArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    
    @PutMapping("/{answerId}")
    public ResponseEntity<Answer> editAnswer( @PathVariable("answerId") UUID answerId, 
                              @RequestBody Answer updatedAnswer){
        
        Answer answer= answerService.editAnswer(answerId, updatedAnswer);
        
        return ResponseEntity.status(HttpStatus.OK).body(answer);
    }

    @PostMapping("/{answerId}/comments")
    public ResponseEntity<Comment> addComment( @PathVariable("answerId") UUID answerId, 
                                               @RequestBody Comment newComment){
        
        Comment comment= answerService.addComment(answerId, newComment);

        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @PostMapping("/{answerId}/likes")
    public ResponseEntity<?> addLike(@PathVariable("answerId") UUID answerId){

        answerService.addLike(answerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
