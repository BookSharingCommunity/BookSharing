package com.booksharing.apisystem.controller;

import com.booksharing.apisystem.model.UserAccount;
import com.booksharing.apisystem.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;

    //Registers a new user and returns their information.
    @PostMapping("/users")
    public UserAccount add(@RequestBody UserAccount user) {
        return userAccountService.saveUser(user);
    }

    //Deletes user from database
    @DeleteMapping("/users")
    public String remove(@RequestBody UserAccount user) { return userAccountService.removeUser(user);}

    //Returns all users' information
    @GetMapping("/users")
    public List<UserAccount> getAll() {
        return userAccountService.getAllUsers();
    }

    //Returns the user object on successful login
    @GetMapping("/users/{email}/login")
    public UserAccount loginUser(@PathVariable String email, @RequestBody UserAccount user) {
        return userAccountService.loginUser(user);
    }


    //Password Calls
    //Gets the user's password and sends it.
    @GetMapping("/passwords/{email}")
    public String forgotPassword(@PathVariable String email) {
        return userAccountService.getPassword(email);
    }

    //Updates the user's password
    @PutMapping("/passwords/{email}")
    public String updatePassword(@PathVariable String email, @RequestBody UserAccount user) {
        return userAccountService.updatePassword(email, user);
    }
}
