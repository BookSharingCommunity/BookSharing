package com.booksharing.apisystem.service;

import com.booksharing.apisystem.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserAccountService {
    public User addUser(User user);
    public List<User> getAllUsers();
    public User getUser(String email);
    public UserDetails verifyUser(User user);
    public String removeUser(User user);
    public String updatePassword(String email, User user);
}
