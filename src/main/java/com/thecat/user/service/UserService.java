package com.thecat.user.service;

import com.thecat.user.model.User;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {
    

    public User findByName( String name ) {
        return User.find( "name", name ).firstResult();
    }

    public User findByEmail( String email ) {
        return User.find( "email", email ).firstResult();
    }

    public User loginUser( String email, String password) {
        return User.find("email = ?1 and password = ?2" , email, password ).firstResult();
    }
}
