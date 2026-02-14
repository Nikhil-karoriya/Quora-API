package com.example.QuoraAPI.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuoraAPI.models.Answer;
import com.example.QuoraAPI.models.LikeAnswer;
import com.example.QuoraAPI.models.User;

@Repository
public interface LikeAnswerRepository extends JpaRepository<LikeAnswer,UUID>{

    public LikeAnswer findByUserAndAnswer(User user, Answer answer);
}
