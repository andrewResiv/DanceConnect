package com.andrew.danceconnect.DanceConnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private Long reviewerId;
    private Long revieweeId;
    private String comment;
    private Integer rating;  // Оценка от 1 до 5
}