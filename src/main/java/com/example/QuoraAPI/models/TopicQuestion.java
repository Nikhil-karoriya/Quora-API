package com.example.QuoraAPI.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicQuestion extends BaseModel {
    
    @ManyToOne
    @JoinColumn(name = "topic_Id", nullable = false)
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "question_Id", nullable = false)
    private Question question;
}
