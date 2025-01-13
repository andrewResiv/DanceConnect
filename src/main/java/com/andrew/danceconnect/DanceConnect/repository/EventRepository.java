package com.andrew.danceconnect.DanceConnect.repository;

import com.andrew.danceconnect.DanceConnect.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByLocationAndDateBetween(String location, LocalDateTime startDate, LocalDateTime endDate);
    List<Event> findByCreatorId(Long creatorId);
}
