package com.andrew.danceconnect.DanceConnect.dto;

import com.andrew.danceconnect.DanceConnect.enums.DanceStyle;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DancePartnerRequestDTO {
    private Long userId;

    @Enumerated(EnumType.STRING)
    private Enum<DanceStyle> danceStyle;

    private String location;
    private String description;
    private Boolean isActive;  // Статус заявки: активная или завершена
}