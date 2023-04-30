package com.booksharing.apisystem.repository;

import com.booksharing.apisystem.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    public Inventory findByInvId(long invId);

    @Query("SELECT r FROM Inventory r WHERE r.isbn LIKE %:search% OR r.title LIKE %:search%")
    public List<Inventory> searchForMatch(@Param("search") String search);
}
