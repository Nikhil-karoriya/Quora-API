package com.example.QuoraAPI.services;

import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.models.Answer;
import com.example.QuoraAPI.models.Question;

@Service
public class QuestionService {

    public Question addQuestion(Question newQuestion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addQuestion'");
    }

    public Set<Question> getQuestion(String text, String tag) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getQuestion'");
    }

    public Answer addAnswer(UUID questionId, Answer newAnswer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAnswer'");
    }

    public void addLike(UUID questionId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addLike'");
    }
    
}
