package com.booksharing.apisystem.repository;

import com.booksharing.apisystem.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    //Gives access to the user_account table in MySql Database

    //Finds an entry by the pk.
    // MySql: SELECT * FROM user_account WHERE email=<EMAIL>;
    Optional<UserAccount> findByEmail(String email);
}
