package com.example.QuoraAPI.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.models.Answer;
import com.example.QuoraAPI.models.Question;
import com.example.QuoraAPI.models.User;
import com.example.QuoraAPI.repositories.AnswerRepository;
import com.example.QuoraAPI.repositories.QuestionRepository;
import com.example.QuoraAPI.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    private final QuestionRepository questionRepository;
    
    private final UserRepository userRepository;
    
    public Answer addAnswer(UUID questionId, UUID userId, String text) {
        
        Question question= questionRepository.findById(questionId).get();

        User user= userRepository.findById(userId).get();
        
        Answer newAnswer= Answer.builder()
                                .user(user)
                                .question(question)
                                .build();

        return answerRepository.save(newAnswer);                                         
    }

    public Answer editAnswer(UUID answerId, String text) {

        Answer answer= answerRepository.findById(answerId).get();

        answer.setText(text);

        return answerRepository.save(answer);
    }
    
}
