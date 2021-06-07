package com.thecat.user.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.thecat.user.model.User;
import com.thecat.user.service.UserService;

@Path("/user")
public class UserResources {
    
    @Inject
    UserService userService;

    @GET
    @Path( "health" )
    @Produces(MediaType.TEXT_PLAIN)
    public String health() {

        User u = userService.findByName( "System Admin" );
        return u.toString();
    }

    @POST
    @Path( "/login/")
    @Consumes( MediaType.APPLICATION_JSON)
    @Produces( MediaType.APPLICATION_JSON)
    public UserResult login( User user ) {
        UserResult response;


        if ( user != null && user.email != null && user.password != null ) {
            User u = userService.loginUser( user.email, user.password );
            
            if ( u != null )
                response = new UserResult( true, "User found", u );
            else
                response = new UserResult( false, "User not found", null );
        } else 
            response = new UserResult( false, "Missing parameter(s)", null );

        return response;
    }

    public class UserResult {
        public boolean success;
        public String message;
        public User user;

        public UserResult() {}

        public UserResult(boolean success, String message, User user) {
            this.success = success;
            this.message = message;
            this.user = user;
        }
    }

}