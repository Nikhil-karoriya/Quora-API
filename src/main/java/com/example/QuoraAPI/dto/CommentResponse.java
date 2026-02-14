package com.example.QuoraAPI.dto;

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

    private UUID userId;

    private UUID answerId;

    private String text;

    private UUID parentCommentId;
    
}
