package com.example.QuoraAPI.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.QuoraAPI.models.Question;
import com.example.QuoraAPI.repositories.QuestionRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    private final String UNIVERSAL_TAG = "universal";

    private final String DEFAULT_TEXT= ".";
    
    @Transactional
    public Question addQuestion(Question question) {

        return questionRepository.save(question);
    }

    public List<Question> getQuestion(String text, String tag) {
        
        if(tag == null) tag= UNIVERSAL_TAG;

        if(text == null) text= DEFAULT_TEXT;
        
        return questionRepository.findByTextAndTag(text, tag);

    } 
}
