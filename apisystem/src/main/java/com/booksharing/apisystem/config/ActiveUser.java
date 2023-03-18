package com.booksharing.apisystem.config;

import com.booksharing.apisystem.model.Role;
import com.booksharing.apisystem.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ActiveUser implements UserDetails {
    private User user;

    public ActiveUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        Role role = user.getRole();
        auths.add(new SimpleGrantedAuthority(role.getName()));
        if (role.getName().equals("ROLE_USER")) {
            auths.add(new SimpleGrantedAuthority("BASIC"));
        }
        else if (role.getName().equals("ROLE_ADMIN")) {
            auths.add(new SimpleGrantedAuthority("BASIC"));
            auths.add(new SimpleGrantedAuthority("ADVANCED"));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
