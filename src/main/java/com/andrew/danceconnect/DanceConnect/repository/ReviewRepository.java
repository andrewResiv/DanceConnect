package com.andrew.danceconnect.DanceConnect.repository;

import com.andrew.danceconnect.DanceConnect.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByRevieweeId(Long revieweeId);
    List<Review> findByReviewerId(Long reviewerId);
    Double findAverageRatingByRevieweeId(Long revieweeId); // Можно использовать кастомный запрос.
}