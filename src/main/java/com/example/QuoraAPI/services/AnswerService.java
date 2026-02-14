package com.example.QuoraAPI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.dto.AnswerRequest;
import com.example.QuoraAPI.dto.AnswerResponse;
import com.example.QuoraAPI.models.Answer;
import com.example.QuoraAPI.models.Question;
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
    
    public AnswerResponse addAnswer(UUID questionId, AnswerRequest answerRequest) {
        
        Question question;
        
        try{
            question= questionRepository.findById(questionId).get();
        }
        catch(NoSuchElementException ne){
            return AnswerResponse.builder().build();
        }
        
        Answer answer= Answer.builder()
                             .text(answerRequest.getText())
                             .user(userRepository.findById(answerRequest.getUserId()).get())
                             .build();

        answer.setQuestion(question);

        answerRepository.save(answer);
        
        return creatAnswerResponse(answer);
    }

    public AnswerResponse editAnswer(UUID answerId, String text) {

        Answer answer;
        
        try{
            answer= answerRepository.findById(answerId).get();
        }
        catch(NoSuchElementException ne){
            return AnswerResponse.builder().build();
        }

        answer.setText(text);

        answerRepository.save(answer);

        return creatAnswerResponse(answer);
    }

    public List<AnswerResponse> getAllAnswers() {
        
        List<Answer> answers= answerRepository.findAll();

        List<AnswerResponse> responses= new ArrayList<>();
        
        for(Answer answer: answers){
            responses.add(creatAnswerResponse(answer));
        }

        return responses;

    }

    private AnswerResponse creatAnswerResponse(Answer answer){
        return AnswerResponse.builder()
                             .text(answer.getText())
                             .userId(answer.getUser().getId())
                             .questionId(answer.getQuestion().getId())
                             .answerId(answer.getId())
                             .build();
    }

    
}
