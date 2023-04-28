package com.booksharing.apisystem.repository;

import com.booksharing.apisystem.model.Review;
import com.booksharing.apisystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    public List<Review> findRatingByReviewId(long reviewId);
    @Query("SELECT r FROM Review r WHERE r.userId = :userId AND r.serviceType = :type")
    public List<Review> getUserBuyerRatings(@Param("userId") User userId, @Param("type") String type);
    public List <Review> findByUserId(long userId);
}
