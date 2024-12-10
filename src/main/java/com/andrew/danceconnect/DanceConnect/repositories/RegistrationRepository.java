package com.andrew.danceconnect.DanceConnect.repositories;

import com.andrew.danceconnect.DanceConnect.models.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByEventId(Long eventId);
    List<Registration> findByUserId(Long userId);
    boolean existsByEventIdAndUserId(Long eventId, Long userId);
}
