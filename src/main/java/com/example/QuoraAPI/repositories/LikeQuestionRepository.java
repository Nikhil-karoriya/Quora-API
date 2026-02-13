package com.example.QuoraAPI.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuoraAPI.models.LikeQuestion;

@Repository
public interface LikeQuestionRepository extends JpaRepository<LikeQuestion,UUID>{

    
}
