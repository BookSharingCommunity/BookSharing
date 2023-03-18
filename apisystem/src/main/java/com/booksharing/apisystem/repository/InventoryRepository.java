package com.booksharing.apisystem.repository;

import com.booksharing.apisystem.model.Book;
import com.booksharing.apisystem.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    public List<Inventory> findInventoryByBookId(Book bookId);
    public Inventory findByInvId(long invId);
}
