package com.example.QuoraAPI.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.dto.LikeRequest;
import com.example.QuoraAPI.models.Answer;
import com.example.QuoraAPI.models.Comment;
import com.example.QuoraAPI.models.LikeAnswer;
import com.example.QuoraAPI.models.LikeComment;
import com.example.QuoraAPI.models.LikeQuestion;
import com.example.QuoraAPI.models.Question;
import com.example.QuoraAPI.models.User;
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
        
        User user= userRepository.findById(userId).get();

        if(type.equals(ANSWERS) ){
        
            Answer answer= answerRepository.findById(id).get();

            LikeAnswer newLike= likeAnswerRepository.findByUserAndAnswer(user, answer);

            if(newLike == null){
                
                newLike= LikeAnswer.builder()
                                   .user(user)
                                   .answer(answer)
                                   .build(); 

                likeAnswerRepository.save(newLike);
            }
        }

        if(type.equals(QUESTIONS)){

            Question question= questionRepository.findById(id).get();

            LikeQuestion newLike= likeQuestionRepository.findByUserAndQuestion(user, question);

            if(newLike == null){

                newLike = LikeQuestion.builder()
                                      .user(user)
                                      .question(question)
                                      .build();

                likeQuestionRepository.save(newLike);
            }
        }

        if(type.equals(COMMENTS)){

            Comment comment= commentRepository.findById(id).get();
        
            LikeComment newLike= likeCommentRepository.findByUserAndComment(user, comment);

            if(newLike == null){
                
                newLike = LikeComment.builder()
                                     .user(user)
                                     .comment(comment)
                                     .build();
                
                likeCommentRepository.save(newLike);
            }
        }
    }
    
}
