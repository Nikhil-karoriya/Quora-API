package com.example.QuoraAPI.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuoraAPI.models.Comment;
import com.example.QuoraAPI.models.LikeComment;
import com.example.QuoraAPI.models.User;

@Repository
public interface LikeCommentRepository extends JpaRepository<LikeComment,UUID>{

    public LikeComment findByUserAndComment(User user, Comment comment);
}
