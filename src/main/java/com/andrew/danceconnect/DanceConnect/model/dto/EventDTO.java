package com.andrew.danceconnect.DanceConnect.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private Long creatorId;
    private String name;
    private String location;
    private LocalDateTime date;
    private String description;
    private String eventType;  // Например, "workshop", "party"
}
