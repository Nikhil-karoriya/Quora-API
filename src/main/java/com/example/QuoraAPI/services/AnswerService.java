package com.example.QuoraAPI.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.dto.AnswerRequest;
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
    
    public Answer addAnswer(UUID questionId, AnswerRequest answerRequest) {
        
        Question question;
        
        User user;

        question= questionRepository.findById(questionId).get();

        user= userRepository.findById(answerRequest.getUserId()).get();
        
        Answer answer= Answer.builder()
                             .text(answerRequest.getText())
                             .user(user)
                             .question(question)
                             .build();

        return answerRepository.save(answer);
    }

    public Answer editAnswer(UUID answerId, String text) {

        Answer answer;
        
        answer= answerRepository.findById(answerId).get();

        answer.setText(text);

        return answerRepository.save(answer);

    }

    public List<Answer> getAllAnswers() {
        
        List<Answer> answers= answerRepository.findAll();

        return answers;

    }
    
}
