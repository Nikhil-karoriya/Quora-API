package com.example.QuoraAPI.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerResponse {
    
    private UUID answerId;
    
    private String text;

    private String user;

    private String question;

    private List<String> comments;

    private List<String> likes;
}
