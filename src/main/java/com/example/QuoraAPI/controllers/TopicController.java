package com.example.QuoraAPI.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuoraAPI.services.TopicService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/topics")
@AllArgsConstructor
public class TopicController {
    
    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<String> createTopic(@RequestBody(required = true) String newTopic){

        topicService.createTopic(newTopic);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(newTopic);
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllTopics(){

        List<String> topics = topicService.getAllTopics();

        return ResponseEntity.status(HttpStatus.OK).body(topics);
    }
}
