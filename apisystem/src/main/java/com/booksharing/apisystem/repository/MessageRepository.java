package com.booksharing.apisystem.repository;

import com.booksharing.apisystem.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("SELECT r FROM Message r WHERE r.threadId = :threadId ORDER BY r.msgId")
    public List<Message> searchByMatch(@Param("threadId") Long threadId);

    public long deleteByThreadId(Long threadId);
}
