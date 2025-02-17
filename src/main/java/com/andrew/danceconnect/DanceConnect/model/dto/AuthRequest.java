package com.andrew.danceconnect.DanceConnect.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Setter
@Getter
public class AuthRequest {
    private String username;
    private String password;
}