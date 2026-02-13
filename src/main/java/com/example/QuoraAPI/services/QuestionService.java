package com.example.QuoraAPI.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.dto.CreateQuestionRequest;
import com.example.QuoraAPI.dto.QuestionResponse;
import com.example.QuoraAPI.models.Question;
import com.example.QuoraAPI.models.Topic;
import com.example.QuoraAPI.models.TopicQuestion;
import com.example.QuoraAPI.models.User;
import com.example.QuoraAPI.repositories.QuestionRepository;
import com.example.QuoraAPI.repositories.TopicQuestionRepository;
import com.example.QuoraAPI.repositories.TopicRepository;
import com.example.QuoraAPI.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    
    private final UserRepository userRepository;

    private final TopicRepository topicRepository;

    private final TopicQuestionRepository topicQuestionRepository;

    private final String UNIVERSAL_TAG = "universal";
    
    @Transactional
    public QuestionResponse addQuestion(CreateQuestionRequest questionDto) {

        User user= userRepository.findById(questionDto.getUserId()).get();

        List<String> topics= questionDto.getTopics();
        
        if(topics == null) topics = new ArrayList<String>();

        Question question= Question.builder()
                                      .title(questionDto.getTitle())
                                      .body(questionDto.getBody())
                                      .user(user)
                                      .topicQuestions(new HashSet<TopicQuestion>())
                                      .build();

        question= questionRepository.save(question);
        
        for(String topicName: topics){
            
            Topic topic= topicRepository.findByName(topicName.toLowerCase());

            if(topic == null){

                topic= topicRepository.save(Topic.builder()
                                                 .name(topicName.toLowerCase())
                                                 .build());
            }

            TopicQuestion newTopicQuestion= TopicQuestion.builder()
                                                         .question(question)
                                                         .topic(topic)
                                                         .build();

            topicQuestionRepository.save(newTopicQuestion);   
            
            question.getTopicQuestions().add(newTopicQuestion);
        }

        addUniversalTag(question);

        return mapToResponse(question);
    }

    public List<QuestionResponse> getQuestion(String text, String tag) {
        
        if(tag == null) tag= UNIVERSAL_TAG;
        
        List<Question> questions= questionRepository.findByTextAndTag(text, tag);

        List<QuestionResponse> response= new ArrayList<>();
        
        for(Question question: questions){
            
            response.add(mapToResponse(question));
        }

        return response;
    }

    private void addUniversalTag(Question question){

        Topic topic= topicRepository.findByName(UNIVERSAL_TAG);

        if(topic == null){

            topic= Topic.builder()
                        .name(UNIVERSAL_TAG)
                        .build();

            topicRepository.save(topic);
        }

        TopicQuestion newTopicQuestion= TopicQuestion.builder()
                                                        .question(question)
                                                        .topic(topic)
                                                        .build();

        topicQuestionRepository.save(newTopicQuestion);

        question.getTopicQuestions().add(newTopicQuestion);
    }

    public QuestionResponse mapToResponse(Question question) {

        List<String> topicNames = question.getTopicQuestions()
                .stream()
                .map(tq -> tq.getTopic().getName())
                .toList();

        return QuestionResponse.builder()
                .id(question.getId())
                .title(question.getTitle())
                .body(question.getBody())
                .username(question.getUser().getUsername())
                .topics(topicNames)
                .build();
    }

}
