package com.ass.brainbitesprototype.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

// Hold non-security-related information of a User to be displayed in views.
public class CustomUser extends User {
    private String firstName;
    private String lastName;

    @Autowired
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String firstName, String lastName) {
        super(username, password, authorities);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}