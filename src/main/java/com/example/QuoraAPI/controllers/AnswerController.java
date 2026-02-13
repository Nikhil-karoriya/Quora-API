package com.example.QuoraAPI.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuoraAPI.dto.AnswerRequest;
import com.example.QuoraAPI.dto.AnswerResponse;
import com.example.QuoraAPI.services.AnswerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/answers")
@AllArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/{questionId}") 
    public ResponseEntity<AnswerResponse> addAnswer( @PathVariable("questionId") UUID questionId, 
                                             @RequestBody AnswerRequest answerRequest){
        
        AnswerResponse response= answerService.addAnswer(questionId, answerRequest);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{answerId}")
    public ResponseEntity<AnswerResponse> editAnswer(@PathVariable("answerId") UUID answerId, 
                                             @RequestBody(required = true) String text){
        
        AnswerResponse response= answerService.editAnswer(answerId, text);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AnswerResponse>> getAllAnswers(){
        
        List<AnswerResponse> response= answerService.getAllAnswers();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
