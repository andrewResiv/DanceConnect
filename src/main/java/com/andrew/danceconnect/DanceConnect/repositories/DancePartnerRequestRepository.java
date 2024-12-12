package com.andrew.danceconnect.DanceConnect.repositories;

import com.andrew.danceconnect.DanceConnect.models.DancePartnerRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DancePartnerRequestRepository extends JpaRepository<DancePartnerRequest, Long> {
    List<DancePartnerRequest> findByDanceStyleAndLocation(String danceStyle, String location);
    List<DancePartnerRequest> findByUserId(Long userId);
}
