package com.andrew.danceconnect.DanceConnect.model.dto;

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
