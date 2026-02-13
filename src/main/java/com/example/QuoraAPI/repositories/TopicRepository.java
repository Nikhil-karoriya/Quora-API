package com.example.QuoraAPI.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuoraAPI.models.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic,UUID> {

    Topic findByName(String name);
    
}
