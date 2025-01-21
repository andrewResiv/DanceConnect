package com.andrew.danceconnect.DanceConnect.repository;

import com.andrew.danceconnect.DanceConnect.model.entity.Event;
import com.andrew.danceconnect.DanceConnect.model.entity.Registration;
import com.andrew.danceconnect.DanceConnect.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByEventId(Long eventId);
    List<Registration> findByUserId(Long userId);
    boolean existsByEventIdAndUserId(Long eventId, Long userId);
    List<User> findUsersByEventId(Long eventId);
    List<Event> findEventsByUserId(Long userId);

    boolean existsByUserAndEvent(User user, Event event);
    Optional<Registration> findByUserAndEvent(User user, Event event);
}
