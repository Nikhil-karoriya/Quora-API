package com.example.QuoraAPI.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.QuoraAPI.models.Topic;
import com.example.QuoraAPI.repositories.TopicRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    public Topic createTopic(Topic newTopic) {
        
        Topic topic= topicRepository.findByName(newTopic.getName());

        if(topic == null) return topicRepository.save(newTopic);

        return topic;
    }

    public List<Topic> getAllTopics() {

        return topicRepository.findAll();
    }
    
}
