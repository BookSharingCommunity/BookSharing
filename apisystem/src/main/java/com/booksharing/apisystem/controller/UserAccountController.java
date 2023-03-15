package com.booksharing.apisystem.controller;

import com.booksharing.apisystem.model.User;
import com.booksharing.apisystem.service.AuthenticationService;
import com.booksharing.apisystem.service.TokenService;
import com.booksharing.apisystem.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private TokenService tokenService;

    //Registers a new user and returns their information.
    @PostMapping("/register")
    public User add(@RequestBody User user) {
        return userAccountService.addUser(user);
    }

    @PostMapping("/verify")
    public UserDetails verify(@RequestBody User user) {
        return userAccountService.verifyUser(user);
    }

    //Deletes user from database
    @DeleteMapping("/users/delete")
    public String remove(@RequestBody User user) { return userAccountService.removeUser(user);}

    //Returns all users' information
    @GetMapping("/users/getall")
    public List<User> getAll() {
        return userAccountService.getAllUsers();
    }

    @GetMapping("/users/get/{username}")
    public User getUser(@PathVariable String username) {
        return userAccountService.getUser(username);
    }

    //Updates the user's password
    @PutMapping("/passwords/{email}")
    public String updatePassword(@PathVariable String email, @RequestBody User user) {
        return userAccountService.updatePassword(email, user);
    }
    @PostMapping("/token")
    public String token(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }
}
