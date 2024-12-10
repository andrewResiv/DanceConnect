package com.andrew.danceconnect.DanceConnect.models;

import com.andrew.danceconnect.DanceConnect.enums.LevelOfDancing;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "dance_partner_requests")
public class DancePartnerRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private String danceStyle;

    @Enumerated(EnumType.STRING)
    private Enum<LevelOfDancing> level;

    private String location;

    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}