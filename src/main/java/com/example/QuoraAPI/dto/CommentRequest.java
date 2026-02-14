package com.example.QuoraAPI.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    
    private UUID userId;

    private UUID answerId;

    private String text;

    private UUID parentCommentId;
}
