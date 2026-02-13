package com.example.QuoraAPI.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.models.Question;
import com.example.QuoraAPI.models.Topic;
import com.example.QuoraAPI.models.TopicQuestion;
import com.example.QuoraAPI.models.User;
import com.example.QuoraAPI.repositories.QuestionRepository;
import com.example.QuoraAPI.repositories.TopicQuestionRepository;
import com.example.QuoraAPI.repositories.TopicRepository;
import com.example.QuoraAPI.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    
    private final UserRepository userRepository;

    private final TopicRepository topicRepository;

    private final TopicQuestionRepository topicQuestionRepository;

    private final String UNIVERSAL_TAG = "universal";
    
    public Question addQuestion(UUID userId, String title, String body, List<String> topics) {
        
        User user= userRepository.findById(userId).get();

        Question newQuestion= Question.builder()
                                      .user(user)
                                      .title(title)
                                      .body(body)
                                      .build();

        for(String text: topics){
            
            Topic topic= topicRepository.findByName(text);

            if(topic == null){

                topic= Topic.builder()
                            .name(text)
                            .build();

                topicRepository.save(topic);
            }

            TopicQuestion newTopicQuestion= TopicQuestion.builder()
                                                         .question(newQuestion)
                                                         .topic(topic)
                                                         .build();

            topicQuestionRepository.save(newTopicQuestion);                                    
        }

        addUniversalTag(newQuestion);

        return questionRepository.save(newQuestion);
    }

    public List<Question> getQuestion(String text, String tag) {
        
        if(tag == null) tag= UNIVERSAL_TAG;
        
        return questionRepository.findByTextAndTag(text, tag);
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
    }
}
