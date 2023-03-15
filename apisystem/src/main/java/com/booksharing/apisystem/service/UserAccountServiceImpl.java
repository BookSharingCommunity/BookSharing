package com.booksharing.apisystem.service;

import com.booksharing.apisystem.exceptions.*;
import com.booksharing.apisystem.model.User;
import com.booksharing.apisystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public User addUser(User user) {
        //Adds a client to the database if they don't have an account

        //Handle pre-existing accounts
        Optional<User> result = userRepository.findByUsername(user.getUsername());
        if(result.isPresent()) throw new ExistingAccountException(user.getUsername());

        user.setPassword(encoder.encode(user.getPassword()));

        //Add new account
        return userRepository.save(user);
    }

    @Override
    public String removeUser(User user) {
        //Deletes a user from the database

        //Error instance where email is null
        if(user.getUsername() == null) throw new MissingEmailPasswordException();

        //Search for user
        Optional<User> result = userRepository.findByUsername(user.getUsername());

        //User does not exist
        if(result.isEmpty()) throw new EmailNotFoundException(user.getUsername());

        //Delete user
        userRepository.delete(result.get());
        return "Successfully deleted account!";
    }

    @Override
    public List<User> getAllUsers() {
        //Gets and returns account information for all users
        return userRepository.findAll();
    }

    @Override
    public User getUser(String email) {
        //Gets user's account
        Optional<User> result = userRepository.findByUsername(email);
        if(result.isEmpty()) throw new EmailNotFoundException(email);
        return result.get();
    }

    @Override
    public UserDetails verifyUser(User user) {
        Optional<User> result = userRepository.findByEmail(user.getEmail());
        if(result.isEmpty()) throw new ExistingAccountException(user.getEmail());
        UserDetails data = authenticationService.loadUserByUsername(result.get().getEmail());
        if(!encoder.matches(user.getPassword(), data.getPassword())) throw new InvalidPasswordException(user.getEmail());
        return data;
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
}
