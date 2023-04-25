package com.booksharing.apisystem.service;

import com.booksharing.apisystem.exceptions.*;
import com.booksharing.apisystem.model.*;
import com.booksharing.apisystem.model.Thread;
import com.booksharing.apisystem.repository.*;
import com.booksharing.apisystem.requests.NewInventoryRequest;
import com.booksharing.apisystem.requests.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    private InventoryRepository inventoryRepository;

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ReviewRepository reviewRepository;

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
    public User updateUser(String username, UpdateUserRequest request) {
        //Update Password
        Optional<User> temp = userRepository.findByUsername(username);

        if(temp.isEmpty()) throw new RuntimeException("No user was found with the provided username");

        User user = temp.get();
        user.setUserYear(request.getUserYear());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public Inventory addInventory(NewInventoryRequest req) {
        Optional<User> user = userRepository.findByUsername(req.getUsername());
        if(user.isEmpty()) throw new RuntimeException("User does not exist");
        Inventory inventory = new Inventory(user.get(), req.getCond(), req.getPrice(), req.getPicture(), req.getIsbn(), req.getTitle(), req.getVersion());
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
    public Inventory getInventory(long invId) {
        Inventory inventory = inventoryRepository.findByInvId(invId);
        if(inventory == null) throw new RuntimeException();
        return inventory;
    }

    @Override
    public List<Inventory> searchInventory(String search) {
        return inventoryRepository.searchForMatch(search);
    }

    @Override
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public List<Thread> getUserThreads(User user) {
        return threadRepository.findPossibleThreads(user.getUserId());
    }

    @Override
    public Thread addUserThread(String buyer, String seller) {
        Optional<User> buyerObj = userRepository.findByUsername(buyer);
        Optional<User> sellerObj = userRepository.findByUsername(seller);
        if(buyerObj.isEmpty() || sellerObj.isEmpty()) throw new RuntimeException("A non-existent username was provided");
        Thread thread = new Thread();
        thread.setBuyerId(buyerObj.get());
        thread.setSellerId(sellerObj.get());
        return threadRepository.save(thread);
    }

    @Override
    public Thread deleteUserThread(Thread thread) {
        threadRepository.delete(thread);
        messageRepository.deleteByThreadId(thread.getThreadId());
        return thread;
    }

    @Override
    public List<Message> getThreadMessages(Long threadId) {
        return messageRepository.searchByMatch(threadId);
    }

    @Override
    public Message addThreadMessage(Message message) {
        return messageRepository.save(message);
    }
    @Override
    public List<Review> getUserReviews(String username, String type) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new RuntimeException("A non-existent username was provided");
        switch (type) {
            case "buyer":
                return reviewRepository.getUserBuyerRatings(user.get().getUserId(), "buyer");
            case "seller":
                return reviewRepository.getUserBuyerRatings(user.get().getUserId(), "seller");
            default: throw new RuntimeException("An invalid rating time has been provided! Please user buyer or seller!");
        }
    }
    @Override
    public Review addUserReview(String type, float rating, String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new RuntimeException();
        Review review = new Review(type, user.get(), rating);
        return reviewRepository.save(review);
    }
    @Override
    public Review deleteUserReview(Review review){
        reviewRepository.delete(review);
        return review;
    }
}
