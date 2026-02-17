package com.example.QuoraAPI.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuoraAPI.adapters.QuestionAdapter;
import com.example.QuoraAPI.dto.QuestionRequest;
import com.example.QuoraAPI.dto.QuestionResponse;
import com.example.QuoraAPI.models.Question;
import com.example.QuoraAPI.services.QuestionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/questions")
@AllArgsConstructor
public class QuestionController {
    
    private final QuestionService questionService;

    private final QuestionAdapter questionAdapter;

    @PostMapping
    public ResponseEntity<QuestionResponse> addQuestion(@RequestBody QuestionRequest questionDto){

        Question newQuestion= questionAdapter.toQuestion(questionDto);
        
        Question question= questionService.addQuestion(newQuestion);

        QuestionResponse response= questionAdapter.toDto(question);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<QuestionResponse>> getQuestion
                             (@RequestParam(value = "text", required = false) String text, 
                              @RequestParam(value = "tag", required = false) String tag){

        List<Question> questions= questionService.getQuestion(text, tag);                        

        List<QuestionResponse> responses= questionAdapter.toDtoAll(questions);
        
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

}
