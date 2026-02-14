package com.example.QuoraAPI.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseModel{
    
    @Column(nullable = false)
    private String username;

    private String email;

    private String bio;

    @OneToMany(mappedBy = "user")
    private Set<Question> questions;

    @OneToMany(mappedBy = "user")
    private Set<Answer> answers;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "follower")
    private Set<Follow> followSet;

    @OneToMany(mappedBy = "followee")
    private Set<Follow> followerSet;

    @OneToMany(mappedBy = "user")
    private Set<LikeQuestion> likedQuestions;

    @OneToMany(mappedBy = "user")
    private Set<LikeAnswer> likedAnswers;

    @OneToMany(mappedBy = "user")
    private Set<LikeComment> likedComments;

}
