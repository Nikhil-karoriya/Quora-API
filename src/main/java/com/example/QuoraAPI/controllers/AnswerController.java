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
import com.example.QuoraAPI.services.AnswerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/answers")
@AllArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/{questionId}") 
    public ResponseEntity<Answer> addAnswer( @PathVariable("questionId") UUID questionId, 
                                             @RequestBody Answer answer){
        
        answerService.addAnswer(questionId, answer);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }
    
    @PutMapping("/{answerId}")
    public ResponseEntity<Answer> editAnswer(@PathVariable("answerId") UUID answerId, 
                                             @RequestBody(required = true) String text){
        
        Answer answer= answerService.editAnswer(answerId, text);
        
        return ResponseEntity.status(HttpStatus.OK).body(answer);
    }

}
