package com.andrew.danceconnect.DanceConnect.dto;

import com.andrew.danceconnect.DanceConnect.enums.DanceStyle;
import com.andrew.danceconnect.DanceConnect.enums.LevelOfDancing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DancePartnerRequestDTO {
    private Long userId; // ID пользователя, создавшего запрос
    private DanceStyle danceStyle; // Стиль танца
    private LevelOfDancing level; // Уровень танцевания
    private String location; // Локация
    private String description; // Описание запроса
}