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
public class CommentResponse {

    private UUID commentId;

    private String user;

    private String answer;

    private String text;

    private String parentComment;
    
    private List<String> childComments;
}
