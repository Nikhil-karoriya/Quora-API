package com.example.QuoraAPI.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class LikeAnswer extends BaseModel {
    
    @ManyToOne
    @JoinColumn(name = "user_Id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "answer_Id", nullable = false)
    private Answer answer;
}
