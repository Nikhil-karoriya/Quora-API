package com.example.QuoraAPI.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.dto.LikeRequest;
import com.example.QuoraAPI.models.LikeAnswer;
import com.example.QuoraAPI.models.LikeComment;
import com.example.QuoraAPI.models.LikeQuestion;
import com.example.QuoraAPI.repositories.AnswerRepository;
import com.example.QuoraAPI.repositories.CommentRepository;
import com.example.QuoraAPI.repositories.LikeAnswerRepository;
import com.example.QuoraAPI.repositories.LikeCommentRepository;
import com.example.QuoraAPI.repositories.LikeQuestionRepository;
import com.example.QuoraAPI.repositories.QuestionRepository;
import com.example.QuoraAPI.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LikeService {

    private final LikeAnswerRepository likeAnswerRepository;

    private final LikeCommentRepository likeCommentRepository;

    private final LikeQuestionRepository likeQuestionRepository;

    private final UserRepository userRepository;

    private final AnswerRepository answerRepository;

    private final QuestionRepository questionRepository;

    private final CommentRepository commentRepository;

    private final String ANSWERS = "answers";
    
    private final String QUESTIONS = "questions";

    private final String COMMENTS = "comments"; 
    
    public void addLike(String type, UUID id, LikeRequest likeRequest) {

        UUID userId= likeRequest.getUserId();
        
        if(type.equals(ANSWERS) ){
            
            LikeAnswer newLike= LikeAnswer.builder()
                                          .user(userRepository.findById(userId).get())
                                          .answer(answerRepository.findById(id).get())
                                          .build(); 

            likeAnswerRepository.save(newLike);
        }

        if(type.equals(QUESTIONS)){

            LikeQuestion newLike = LikeQuestion.builder()
                                               .user(userRepository.findById(userId).get())
                                               .question(questionRepository.findById(id).get())
                                               .build();

            likeQuestionRepository.save(newLike);
        }

        if(type.equals(COMMENTS)){
            LikeComment newLike = LikeComment.builder()
                                             .user(userRepository.findById(userId).get())
                                             .comment(commentRepository.findById(id).get())
                                             .build();
                
            likeCommentRepository.save(newLike);
        }
    }
    
}
