package com.example.QuoraAPI.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.QuoraAPI.models.Follow;
import com.example.QuoraAPI.models.User;

@Repository
public interface FollowRepository extends JpaRepository<Follow,UUID> {
    
    public Follow findByFollowerAndFollowee(User follower, User followee);
}
