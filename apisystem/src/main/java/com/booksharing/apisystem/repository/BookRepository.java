package com.booksharing.apisystem.repository;

import com.booksharing.apisystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT r FROM Book r WHERE r.isbn LIKE %:search% OR r.title LIKE %:search%")
    public List<Book> searchByMatch(@Param("search") String search);

    //@Query("SELECT r FROM Book r WHERE r.isbn = %:search% AND r.title = %:title%")
    //public List<Book> searchByIsbnAndTitle(@Param("isbn") String isbn, @Param("title") String title);

    public Book findByBookId(long bookId);
}
