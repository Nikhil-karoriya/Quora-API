package com.example.QuoraAPI.controllers;

import java.util.Set;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuoraAPI.models.Answer;
import com.example.QuoraAPI.models.Question;
import com.example.QuoraAPI.services.QuestionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/questions")
@AllArgsConstructor
public class QuestionController {
    
    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody Question newQuestion){

        Question question= questionService.addQuestion(newQuestion);

        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    @GetMapping("/search")
    public ResponseEntity<Set<Question>> getQuestion( @RequestParam(value = "text", required = false) String text, 
                @RequestParam(value = "tag", required = false) String tag){

        Set<Question> questions= questionService.getQuestion(text, tag);

        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    @PostMapping("/{questionId}/answers") 
    public ResponseEntity<Answer> addAnswer( @PathVariable("questionId") UUID questionId, 
                                             @RequestBody Answer newAnswer){
        
        Answer answer= questionService.addAnswer(questionId, newAnswer);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }

    @PostMapping("/{questionId}/likes")
    public ResponseEntity<?> addLike(@PathVariable("questionId") UUID questionId){

        questionService.addLike(questionId);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
