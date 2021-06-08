package com.thecat.user.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

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
        return "SUCCESS";
    }

    @POST
    @Path( "/login")
    @Consumes( MediaType.APPLICATION_JSON)
    public int login( User user ) {
        // First look at if the username exist.
        User u = userService.findByEmail(user.email);

        if ( u != null ) {

            u = userService.loginUser(user.email, user.password);

            if ( u != null ) {
                return Status.OK.getStatusCode();
            } else {
                return Status.PARTIAL_CONTENT.getStatusCode();
            }
        } else
            return Status.NO_CONTENT.getStatusCode();

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