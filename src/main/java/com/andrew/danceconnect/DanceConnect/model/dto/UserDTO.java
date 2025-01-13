package com.andrew.danceconnect.DanceConnect.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;  // Например, "USER", "ADMIN" и т.д.
    private String profilePicture;
    private String bio;
    private String location;
    private BigDecimal rating;
}
