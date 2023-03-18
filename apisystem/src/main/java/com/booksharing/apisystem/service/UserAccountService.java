package com.booksharing.apisystem.service;

import com.booksharing.apisystem.model.Book;
import com.booksharing.apisystem.model.Inventory;
import com.booksharing.apisystem.model.User;
import com.booksharing.apisystem.requests.NewInventoryRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserAccountService {
    public User addUser(User user);
    public List<User> getAllUsers();
    public User getUserByEmail(String email);
    public User getUserByUsername(String username);
    public String removeUser(long userId);
    public String updatePassword(String email, User user);
    public List<Inventory> findBooks(String search);
    public Book addBook(Book book);
    public Book removeBook(long bookId);
    public Inventory addInventory(NewInventoryRequest invreq);
    public Inventory removeInventory(long invId);
}
