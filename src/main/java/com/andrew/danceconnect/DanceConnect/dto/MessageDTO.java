package com.andrew.danceconnect.DanceConnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long senderId;
    private Long receiverId;
    private String content;
    private LocalDateTime sentDate;
    private Boolean isRead;
}