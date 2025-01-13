package com.andrew.danceconnect.DanceConnect.repository;

import com.andrew.danceconnect.DanceConnect.model.entity.DancePartnerRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DancePartnerRequestRepository extends JpaRepository<DancePartnerRequest, Long> {
    List<DancePartnerRequest> findByDanceStyleAndLocation(String danceStyle, String location);
    List<DancePartnerRequest> findByUserId(Long userId);
}
