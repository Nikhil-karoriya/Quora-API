package com.example.QuoraAPI.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.models.Topic;
import com.example.QuoraAPI.repositories.TopicRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    public void createTopic(String newTopic) {
        
        Topic topic= topicRepository.findByName(newTopic);

        if(topic == null){

            topic = Topic.builder().name(newTopic).build();
            
            topicRepository.save(topic);
        }

    }

    public List<String> getAllTopics() {

        return topicRepository.findAll().stream()
                              .map(Topic::getName)
                              .collect(Collectors.toList());
    }
    
}
