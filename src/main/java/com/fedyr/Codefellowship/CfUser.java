package com.fedyr.Codefellowship;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Date;

@Entity
public class CfUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String username;
    String password;
    String firstName;
    String lastName;
    // Storing as a String because our use case doesn't require a Date right now.
    // Plus, form is already giving it to us as a String, so no point in parsing it in/out of String for storage
    // when we will display it as a String anyway.
    // We can parse it to a Date later if/where we need it.
    String dateOfBirth;
    String bio;


    public CfUser() {}

    public CfUser(String username, String password, String firstName, String lastName, String dateOfBirth,
                  String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
