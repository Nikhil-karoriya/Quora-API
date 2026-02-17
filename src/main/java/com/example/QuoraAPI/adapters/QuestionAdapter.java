package com.example.QuoraAPI.adapters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.QuoraAPI.dto.QuestionRequest;
import com.example.QuoraAPI.dto.QuestionResponse;
import com.example.QuoraAPI.models.Answer;
import com.example.QuoraAPI.models.LikeQuestion;
import com.example.QuoraAPI.models.Question;
import com.example.QuoraAPI.models.Topic;
import com.example.QuoraAPI.models.TopicQuestion;
import com.example.QuoraAPI.models.User;
import com.example.QuoraAPI.repositories.TopicQuestionRepository;
import com.example.QuoraAPI.repositories.TopicRepository;
import com.example.QuoraAPI.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class QuestionAdapter {

    private final UserRepository userRepository;
    
    private final TopicRepository topicRepository;

    private final TopicQuestionRepository topicQuestionRepository;

    private final String UNIVERSAL_TAG = "universal";

    private final String DEFAULT_TEXT= ".";

    public Question toQuestion(QuestionRequest questionDto){

        questionDto.setTitle(questionDto.getTitle() + DEFAULT_TEXT);
        questionDto.setBody(questionDto.getBody() + DEFAULT_TEXT);

        Question question= Question.builder()
                                   .title(questionDto.getTitle())
                                   .body(questionDto.getBody())
                                   .user(userRepository.findById(questionDto.getUserId()).get())
                                   .topicQuestions(new HashSet<>())
                                   .build();                               

        List<String> topics= questionDto.getTopics();

        if(topics == null) topics = new ArrayList<String>();

        for(String topicName: topics){
            
            Topic topic= topicRepository.findByName(topicName.toLowerCase());

            if(topic == null){

                topic= Topic.builder()
                            .name(topicName.toLowerCase())
                            .build(); 
                
                topicRepository.save(topic);
            }

            TopicQuestion newTopicQuestion= TopicQuestion.builder()
                                                         .question(question)
                                                         .topic(topic)
                                                         .build(); 

            question.getTopicQuestions().add(newTopicQuestion);
        }

        addUniversalTag(question);

        return question;
    }

    public QuestionResponse toDto(Question question){

        return QuestionResponse.builder()
                .id(question.getId())
                .title(question.getTitle())
                .body(question.getBody())
                .username(question.getUser().getUsername())
                .topics(Optional.ofNullable(question.getTopicQuestions())
                                .orElse(Collections.emptySet())
                                .stream()
                                .map(TopicQuestion::getTopic)
                                .map(Topic::getName)
                                .collect(Collectors.toList()))

                .answers(Optional.ofNullable(question.getAnswers())
                                .orElse(Collections.emptySet())
                                .stream()
                                .map(Answer::getText)
                                .collect(Collectors.toList()))
                .likes(Optional.ofNullable(question.getLikes())
                                .orElse(Collections.emptySet())
                                .stream()
                                .map(LikeQuestion::getUser)
                                .map(User::getUsername)
                                .collect(Collectors.toList()))
                .build();
    }

    public List<QuestionResponse> toDtoAll(List<Question> questions){

        List<QuestionResponse> responses= new ArrayList<>();
        
        for(Question question: questions){
            
            String title= question.getTitle();

            String body= question.getBody();
            
            question.setTitle(title.substring(0,title.length()-1));

            question.setBody(body.substring(0, body.length()-1));
            
            responses.add(toDto(question));
        }

        return responses;
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

        question.getTopicQuestions().add(newTopicQuestion);
    }
}
