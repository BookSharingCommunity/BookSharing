package com.booksharing.apisystem.controller;

import com.booksharing.apisystem.model.*;
import com.booksharing.apisystem.model.Thread;
import com.booksharing.apisystem.requests.NewInventoryRequest;
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

    //Deletes user from database
    @DeleteMapping("/users/remove/{user}")
    public String remove(@PathVariable long user) {
        return userAccountService.removeUser(user);
    }

    //Returns all users' information
    @GetMapping("/users/getall")
    public List<User> getAll() {
        return userAccountService.getAllUsers();
    }

    @GetMapping("/users/get/{username}")
    public User getUser(@PathVariable String username) {
        return userAccountService.getUserByUsername(username);
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

    @PostMapping("/inventory/add")
    public Inventory addInventory(@RequestBody NewInventoryRequest invreq) {
        return userAccountService.addInventory(invreq);
    }

    @DeleteMapping("/inventory/remove/{inventory}")
    public Inventory removeInventory(@PathVariable long inventory) {
        return userAccountService.removeInventory(inventory);
    }

    @GetMapping("inventory/getall")
    public List<Inventory> getAllInventory() {
        return userAccountService.getAllInventories();
    }

    @GetMapping("inventory/search/{request}")
    public List<Inventory> getAllInventory(@PathVariable String request) {
        return userAccountService.searchInventory(request);
    }

    @GetMapping("threads/get/{username}")
    public List<Thread> getAllThreads(@PathVariable String username) {
        return userAccountService.getUserThreads(userAccountService.getUserByUsername(username));
    }

    @PostMapping("threads/add/{buyer}/{seller}")
    public Thread addThread(@PathVariable String buyer, @PathVariable String seller) {

        return userAccountService.addUserThread(buyer, seller);
    }

    @DeleteMapping("threads/delete")
    public Thread deleteThread(@RequestBody Thread thread) {
        return userAccountService.deleteUserThread(thread);
    }

    @GetMapping("messages/get/{threadId}")
    public List<Message> getMessages(@PathVariable Long threadId) {
        return userAccountService.getThreadMessages(threadId);
    }

    @PostMapping("messages/add")
    public Message addMessage(@RequestBody Message msg) {
        return userAccountService.addThreadMessage(msg);
    }

    @PostMapping("review/add")
    public Review addReview(@RequestBody String type, @RequestBody float rating, @RequestBody String username) {
        return userAccountService.addUserReview(type, rating, username);
    }

    @GetMapping("review/get/{username}")
    public List<Review> getUserReviews(@PathVariable Long userid) {
        return userAccountService.getUserReviews(userid);
    }
}
