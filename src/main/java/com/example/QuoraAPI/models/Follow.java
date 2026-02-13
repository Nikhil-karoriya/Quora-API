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
public class Follow extends BaseModel {
    
    @ManyToOne
    @JoinColumn(name = "follower_Id", nullable = false)
    private User follower;

    @ManyToOne
    @JoinColumn(name = "followee_Id", nullable = false)
    private User followee;

}
