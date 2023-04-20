package com.booksharing.apisystem.repository;

import com.booksharing.apisystem.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    public List<Review> findRatingByReviewId(long reviewId);
    public List <Review> findByUserId(long userId);
}
