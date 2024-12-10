package com.andrew.danceconnect.DanceConnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    private Long userId;
    private Long eventId;
    private String registrationDate;
}
