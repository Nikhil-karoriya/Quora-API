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
public class Comment extends BaseModel {
    
    @ManyToOne
    @JoinColumn(name = "user_Id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "answer_Id", nullable = false)
    private Answer answer;

    @OneToMany(mappedBy = "parentComment")
    private Set<Comment> childComments;

    @ManyToOne
    private Comment parentComment;

    @OneToMany(mappedBy = "comment")
    private Set<LikeComment> likes;

    public String getProperComment(){

        return text + " - " + user.getUsername();
    }
}
