package com.example.QuoraAPI.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuoraAPI.adapters.AnswerAdapter;
import com.example.QuoraAPI.dto.AnswerRequest;
import com.example.QuoraAPI.dto.AnswerResponse;
import com.example.QuoraAPI.models.Answer;
import com.example.QuoraAPI.services.AnswerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/answers")
@AllArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    private final AnswerAdapter answerAdapter;

    @PostMapping("/{questionId}") 
    public ResponseEntity<AnswerResponse> addAnswer( @PathVariable("questionId") UUID questionId, 
                                          @Validated @RequestBody AnswerRequest answerRequest){
        
        Answer answer= answerService.addAnswer(questionId, answerRequest);

        AnswerResponse response= answerAdapter.toDto(answer);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{answerId}")
    public ResponseEntity<AnswerResponse> editAnswer(@PathVariable("answerId") UUID answerId, 
                                             @RequestBody(required = true) String text){
        
        Answer answer= answerService.editAnswer(answerId, text);
        
        AnswerResponse response= answerAdapter.toDto(answer);
        
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AnswerResponse>> getAllAnswers(){
        
        List<Answer> answers= answerService.getAllAnswers();
        
        List<AnswerResponse> responses= answerAdapter.toDtoAll(answers);

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

}
