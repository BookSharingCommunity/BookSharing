package com.booksharing.apisystem.controller;

import com.booksharing.apisystem.model.Book;
import com.booksharing.apisystem.model.Inventory;
import com.booksharing.apisystem.model.User;
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
    public String remove(@PathVariable long user) { return userAccountService.removeUser(user);}

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

    @GetMapping("/books/search/{search}")
    public List<Inventory> findBooks(@PathVariable String search) { return userAccountService.findBooks(search);}

    @PostMapping("/books/add")
    public Book addBook(@RequestBody Book book) { return userAccountService.addBook(book); }

    @DeleteMapping("/books/remove/{book}")
    public Book removeBook(@PathVariable long book) { return userAccountService.removeBook(book); }

    @PostMapping("/inventory/add")
    public Inventory addInventory(@RequestBody NewInventoryRequest invreq) { return userAccountService.addInventory(invreq); }

    @DeleteMapping("/inventory/remove/{inventory}")
    public Inventory removeInventory(@PathVariable long inventory) { return userAccountService.removeInventory(inventory); }

    @GetMapping("inventory/getall")
    public List<Inventory> getAllInventory() { return userAccountService.getAllInventories(); }
}
