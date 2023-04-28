package com.booksharing.apisystem.repository;

import com.booksharing.apisystem.model.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Integer> {
    @Query("SELECT r FROM Thread r WHERE r.buyerId = :userId OR r.sellerId = :userId")
    public List<Thread> findPossibleThreads(@Param("userId") Long userId);

    public Optional<Thread> findByThreadId(Long threadId);
}
