package com.booksharing.apisystem.service;

import com.booksharing.apisystem.exceptions.*;
import com.booksharing.apisystem.model.UserAccount;
import com.booksharing.apisystem.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService{

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserAccount saveUser(UserAccount user) {
        //Adds a client to the database if they don't have an account

        //Handle pre-existing accounts
        Optional<UserAccount> result = userAccountRepository.findByEmail(user.getEmail());
        if(result.isPresent()) throw new ExistingAccountException(user.getEmail());

        //Add new account
        return userAccountRepository.save(user);
    }

    @Override
    public UserAccount loginUser(UserAccount user) {
        //Validates the login of a user. Returns the account information if successful.

        //Set up variables
        Optional<UserAccount> result;
        String email;
        String pass;

        //Initialize provided login
        email = user.getEmail();
        pass = user.getPass();

        //Handle null login credential
        if(email == null || pass == null) throw new MissingEmailPasswordException();

        //Initialize query
        result = userAccountRepository.findByEmail(email);

        //User does not exist
        if(result.isEmpty()) throw new EmailNotFoundException(email);

        //Get result
        user = result.get();

        //Error instance where password is incorrect
        if(!user.getPass().equals(pass)) throw new InvalidPasswordException(email);

        //Correct password
        return user;
    }

    @Override
    public String removeUser(UserAccount user) {
        //Deletes a user from the database

        //Error instance where email is null
        if(user.getEmail() == null) throw new MissingEmailPasswordException();

        //Search for user
        Optional<UserAccount> result = userAccountRepository.findByEmail(user.getEmail());

        //User does not exist
        if(result.isEmpty()) throw new EmailNotFoundException(user.getEmail());

        //Delete user
        userAccountRepository.delete(result.get());
        return "Successfully deleted account!";
    }

    @Override
    public List<UserAccount> getAllUsers() {
        //Gets and returns account information for all users
        return userAccountRepository.findAll();
    }

    @Override
    public String getPassword(String email) {
        //Gets and returns the account's password
        Optional<UserAccount> result = userAccountRepository.findByEmail(email);
        if(result.isEmpty()) throw new EmailNotFoundException(email);
        return result.get().getPass();
    }

    @Override
    public String updatePassword(String email, UserAccount user) {
        //Updates the account's password and returns either success message or error message

        //Handle input errors
        if(user.getEmail() == null || user.getPass() == null) throw new NullPasswordChangeAttemptException();
        if(!user.getEmail().equals(email)) throw new RequestEmailInputsException();

        //Find email in database
        Optional<UserAccount> result = userAccountRepository.findByEmail(email);

        //Handle if not found
        if(result.isEmpty()) throw new EmailNotFoundException(email);

        //Update Password
        UserAccount temp = result.get();
        temp.setPass(user.getPass());
        user = temp;

        userAccountRepository.save(user);
        return "Password has been successfully updated for the email: " + email;
    }
}
