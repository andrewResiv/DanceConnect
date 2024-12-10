package com.andrew.danceconnect.DanceConnect.repositories;

import com.andrew.danceconnect.DanceConnect.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByLocationAndDateBetween(String location, LocalDateTime startDate, LocalDateTime endDate);
    List<Event> findByCreatorId(Long creatorId);
}
