package com.booksharing.apisystem.service;

import com.booksharing.apisystem.exceptions.*;
import com.booksharing.apisystem.model.Book;
import com.booksharing.apisystem.model.Inventory;
import com.booksharing.apisystem.model.Role;
import com.booksharing.apisystem.model.User;
import com.booksharing.apisystem.repository.BookRepository;
import com.booksharing.apisystem.repository.InventoryRepository;
import com.booksharing.apisystem.repository.RoleRepository;
import com.booksharing.apisystem.repository.UserRepository;
import com.booksharing.apisystem.requests.NewInventoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public User addUser(User user) {
        //Adds a client to the database if they don't have an account

        //Handle pre-existing accounts
        Optional<User> result = userRepository.findByUsername(user.getUsername());
        if(result.isPresent()) throw new ExistingAccountException(user.getUsername());

        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = roleRepository.findRoleByName("ROLE_USER");
        if(userRole == null) throw new RuntimeException();
        user.setRole(userRole);
        //Add new account
        return userRepository.save(user);
    }

    @Override
    public String removeUser(long id) {
        //Deletes a user from the database
        User user = userRepository.findByUserId(id);
        userRepository.delete(user);
        return "Successfully deleted account!";
    }

    @Override
    public List<User> getAllUsers() {
        //Gets and returns account information for all users
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        //Gets user's account
        Optional<User> result = userRepository.findByEmail(email);
        if(result.isEmpty()) throw new EmailNotFoundException(email);
        return result.get();
    }

    @Override
    public User getUserByUsername(String username) {
        //Gets user's account
        Optional<User> result = userRepository.findByUsername(username);
        if(result.isEmpty()) throw new UsernameNotFoundException(username);
        return result.get();
    }

    @Override
    public String updatePassword(String email, User user) {
        //Updates the account's password and returns either success message or error message

        //Handle input errors
        if(user.getUsername() == null || user.getPassword() == null) throw new NullPasswordChangeAttemptException();
        if(!user.getUsername().equals(email)) throw new RequestEmailInputsException();

        //Find email in database
        Optional<User> result = userRepository.findByUsername(email);

        //Handle if not found
        if(result.isEmpty()) throw new EmailNotFoundException(email);

        //Update Password
        User temp = result.get();
        temp.setPassword(encoder.encode(user.getPassword()));
        user = temp;

        userRepository.save(user);
        return "Password has been successfully updated for the email: " + email;
    }

    @Override
    public List<Inventory> findBooks(String search) {
        List<Inventory> allMatches = new ArrayList<>();
        List<Book> books = bookRepository.searchByMatch(search);
        for(Book book: books) {
            List<Inventory> matches = inventoryRepository.findInventoryByBookId(book);
            if(!matches.isEmpty()) matches.forEach(match -> allMatches.add(match));
        }
        return allMatches;
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book removeBook(long bookId) {
        Book book = bookRepository.findByBookId(bookId);
        if(book == null) throw new RuntimeException();
        bookRepository.delete(book);
        return book;
    }

    @Override
    public Inventory addInventory(NewInventoryRequest req) {
        User user = userRepository.findByUserId(req.getUserId());
        Book book = bookRepository.findByBookId(req.getBookId());
        if(user == null) throw new RuntimeException("Invalid User Id was provided!");
        if(book == null) throw new RuntimeException("Invalid Book Id was provided!");
        Inventory inventory = new Inventory(user, book, req.getCond(), req.getPrice(), req.getPfp());
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory removeInventory(long invId) {
        Inventory inventory = inventoryRepository.findByInvId(invId);
        if(inventory == null) throw new RuntimeException();
        inventoryRepository.delete(inventory);
        return inventory;
    }

    @Override
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }
}
