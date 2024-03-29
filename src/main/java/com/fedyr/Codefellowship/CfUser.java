package com.fedyr.Codefellowship;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class CfUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(unique = true)
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

    @ManyToMany
    Set<CfUser> following;

    //TODO: refactor to use just following.
    @ManyToMany
    Set<CfUser> followers;

    @OneToMany(mappedBy = "createdBy")
    List<Post> postList;


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

    public long getId() {
        return id;
    }

    public Set<CfUser> getFollowing() {
        return following;
    }

    public Set<CfUser> getFollowers() {
        return followers;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public void setFollowing(Set<CfUser> following) {
        this.following = following;
    }

    public void setFollowers(Set<CfUser> followers) {
        this.followers = followers;
    }
}
