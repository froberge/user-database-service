package com.thecat.user.endpoint;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.thecat.user.model.User;
import com.thecat.user.service.UserService;

import org.jboss.logging.Logger;

@Path("/user")
public class UserResource {
    
    @Inject
    UserService userService;

    private static final Logger LOG = Logger.getLogger(UserResource.class);

    @GET
    @Path( "health" )
    @Produces(MediaType.TEXT_PLAIN)
    public int health() {
        return Response.Status.OK.getStatusCode();
    }

    @POST
    @Path( "/login")
    @Consumes( MediaType.APPLICATION_JSON)
    public int login( User user ) {

        LOG.info( "Login rest endpoint " + user.toString() + " " + user.password );

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
}