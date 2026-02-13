package com.example.QuoraAPI.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topic extends BaseModel{
    
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "topic")
    private Set<TopicQuestion> topicQuestions;
}
