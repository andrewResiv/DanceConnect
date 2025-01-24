package com.andrew.danceconnect.DanceConnect.model.dto;

import com.andrew.danceconnect.DanceConnect.model.constant.DanceStyle;
import com.andrew.danceconnect.DanceConnect.model.constant.LevelOfDancing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DancePartnerRequestDTO {
    private Long userId; // ID пользователя, создавшего запрос
    private DanceStyle danceStyle; // Стиль танца
    private LevelOfDancing level; // Уровень танца
    private String location; // Локация
    private String description; // Описание запроса
}