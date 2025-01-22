package com.andrew.danceconnect.DanceConnect.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserDTO {
    private Long id;
    private String username; // Упрощенная версия пользователя
}
