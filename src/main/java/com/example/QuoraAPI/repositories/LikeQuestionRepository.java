package com.example.QuoraAPI.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuoraAPI.models.LikeQuestion;
import com.example.QuoraAPI.models.Question;
import com.example.QuoraAPI.models.User;

@Repository
public interface LikeQuestionRepository extends JpaRepository<LikeQuestion,UUID>{

    public LikeQuestion findByUserAndQuestion(User user, Question question);    
}
