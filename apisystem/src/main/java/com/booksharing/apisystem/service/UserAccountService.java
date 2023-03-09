package com.booksharing.apisystem.service;

import com.booksharing.apisystem.model.UserAccount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserAccountService {
    public UserAccount saveUser(UserAccount user);
    public List<UserAccount> getAllUsers();
    public UserAccount loginUser(UserAccount user);
    public String removeUser(UserAccount user);
    public String getPassword(String email);
    public String updatePassword(String email, UserAccount user);
}
