package com.booksharing.apisystem.repository;

import com.booksharing.apisystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //Gives access to the user_account table in MySql Database

    //Finds an entry by the pk.
    // MySql: SELECT * FROM user_account WHERE username=<USERNAME>;
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
