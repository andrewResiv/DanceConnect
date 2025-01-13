package com.andrew.danceconnect.DanceConnect.repository;

import com.andrew.danceconnect.DanceConnect.model.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByEventId(Long eventId);
    List<Registration> findByUserId(Long userId);
    boolean existsByEventIdAndUserId(Long eventId, Long userId);
}
