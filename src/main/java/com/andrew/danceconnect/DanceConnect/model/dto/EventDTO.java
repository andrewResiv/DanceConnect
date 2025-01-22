package com.andrew.danceconnect.DanceConnect.model.dto;

import com.andrew.danceconnect.DanceConnect.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    @NotNull(message = "Creator ID не может быть пустым")
    private SimpleUserDTO creator;

    @NotBlank(message = "Имя события обязательно")
    private String name;

    @NotBlank(message = "Локация обязательна")
    private String location;

    @NotNull(message = "Дата события обязательна")
    private LocalDateTime date;

    private String description;
}
