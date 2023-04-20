package com.booksharing.apisystem.repository;

import com.booksharing.apisystem.model.Book;
import com.booksharing.apisystem.model.Inventory;
import com.booksharing.apisystem.model.Message;
import com.booksharing.apisystem.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.booksharing.apisystem.model.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    public List<Review> findRatingByReviewId(long reviewId);
    public List <Review> findByUserId(long userId);
}
