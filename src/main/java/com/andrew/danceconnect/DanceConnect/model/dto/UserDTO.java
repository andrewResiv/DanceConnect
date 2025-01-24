package com.andrew.danceconnect.DanceConnect.model.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotNull(message = "Username is required")
    private String username;

    @Email(message = "Invalid email format")
    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Password is required")
    private String password;

    private String firstName;
    private String lastName;

    @NotNull(message = "Role is required")
    private String role;

    private String profilePicture;
    private String bio;
    private String location;

    @DecimalMin(value = "0.0", inclusive = true, message = "Rating must be at least 0")
    @DecimalMax(value = "5.0", inclusive = true, message = "Rating must be no more than 5")
    private BigDecimal rating;
}
