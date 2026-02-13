package com.example.QuoraAPI.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuoraAPI.dto.LikeRequest;
import com.example.QuoraAPI.services.LikeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/likes/")
@AllArgsConstructor
public class LikeController {

    private final LikeService likeService;
    
    @PostMapping("/{type}/{id}")
    public ResponseEntity<?> addLike(@PathVariable("type") String type,
                                     @PathVariable("id") UUID id,
                                     @RequestBody LikeRequest likeRequest){

        likeService.addLike(type, id, likeRequest);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
