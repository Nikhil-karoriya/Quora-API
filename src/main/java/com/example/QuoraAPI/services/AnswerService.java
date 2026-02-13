package com.example.QuoraAPI.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.models.Answer;
import com.example.QuoraAPI.models.Comment;

@Service
public class AnswerService {

    public Answer editAnswer(UUID answerId, Answer updatedAnswer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editAnswer'");
    }

    public Comment addComment(UUID answerId, Comment newComment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addComment'");
    }

    public void addLike(UUID answerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addLike'");
    }
    
}
