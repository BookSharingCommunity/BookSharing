package com.booksharing.apisystem.service;

import com.booksharing.apisystem.model.*;
import com.booksharing.apisystem.model.Thread;
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

    public Inventory addInventory(NewInventoryRequest invreq);

    public Inventory removeInventory(long invId);

    public List<Inventory> searchInventory(String search);
    public Inventory getInventory(long invId);

    public List<Inventory> getAllInventories();

    public List<Thread> getUserThreads(User user);

    public Thread addUserThread(String buyer, String seller);

    public Thread deleteUserThread(Thread thread);

    public List<Message> getThreadMessages(Long threadId);

    public Message addThreadMessage(Message message);

    public List<Review> getUserReviews(String username, String type);

    public Review addUserReview(String type, float rating, String username);
    public Review deleteUserReview(Review review);

}
