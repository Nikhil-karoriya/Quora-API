package com.example.QuoraAPI.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Answer extends BaseModel{
    
    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_Id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_Id", nullable = false)
    private Question question;

    @OneToMany(mappedBy = "answer")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "answer")
    private Set<LikeAnswer> likes;
}
