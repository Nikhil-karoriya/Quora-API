package com.example.QuoraAPI.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuoraAPI.models.Question;
import com.example.QuoraAPI.services.QuestionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/questions")
@AllArgsConstructor
public class QuestionController {
    
    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){

        questionService.addQuestion(question);

        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Question>> getQuestion
                             (@RequestParam(value = "text", required = false) String text, 
                              @RequestParam(value = "tag", required = false) String tag){

        List<Question> questions= questionService.getQuestion(text, tag);

        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

}
