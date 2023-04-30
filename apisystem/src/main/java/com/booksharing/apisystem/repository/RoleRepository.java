package com.booksharing.apisystem.repository;

import com.booksharing.apisystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findRoleByName(String name);
}
