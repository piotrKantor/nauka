package com.example.rs;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserResource {

    @GET
    @Path("/get")
    @PermitAll
    public Response getUser(){
        String message="There are no users in this application yet";
        return Response.serverError().entity(message).type(MediaType.TEXT_PLAIN_TYPE).build();
    }
}
