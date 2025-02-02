package com.andrew.danceconnect.DanceConnect.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 1, max = 50, message = "username should be greater than 1 and less than 50")
    private String username;

    @Column(nullable = false)
    @Size(min = 1, max = 255, message = "password should be greater than 1 and less than 255")
    private String password;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private String role;

    private String profilePicture;

    private String bio;

    private String location;

    @Column(precision = 3, scale = 2)
    private BigDecimal rating = BigDecimal.ZERO;
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DancePartnerRequest> dancePartnerRequests; // Запросы, созданные пользователем

    @ToString.Exclude
    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<DancePartnerRequest> partneredRequests; // Запросы, где пользователь является партнером

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> createdEvents;

    @OneToMany(mappedBy = "reviewee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> receivedReviews;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> writtenReviews;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> receivedMessages;

    public void addDancePartnerRequest(DancePartnerRequest request) {
        if (!dancePartnerRequests.contains(request)) { // Обязательно устанавливаем связь с пользователем
            dancePartnerRequests.add(request);
            request.setUser(this);
        }
    }

    public void removeDancePartnerRequest(DancePartnerRequest request) {
        dancePartnerRequests.remove(request);
        request.setUser(null);  // Обнуляем связь с пользователем
    }
}
