package com.andrew.danceconnect.DanceConnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DancePartnerRequestDTO {
    private Long id;
    private Long userId;
    private String danceStyle;
    private String location;
    private String description;
    private Boolean isActive;  // Статус заявки: активная или завершена
}