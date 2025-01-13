package com.andrew.danceconnect.DanceConnect.model.entity;

import com.andrew.danceconnect.DanceConnect.model.constant.DanceStyle;
import com.andrew.danceconnect.DanceConnect.model.constant.LevelOfDancing;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
    private DanceStyle danceStyle;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LevelOfDancing level;

    private String location;

    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}