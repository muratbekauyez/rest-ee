package com.example.rest_ee;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("users")
public class UserResource {

    UserRepository userRepository = new UserRepository();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("{id}")
    public User getUser(@PathParam("id") int id) {
        return userRepository.getUser(id);
    }

    @POST
    @Path("new")
    public User createUser(User user) {
        userRepository.createUser(user);
        return user;
    }

    @PUT
    @Path("update")
    public User updateUser(User user) {
        if (userRepository.getUser(user.getId()).getId() == 0) {
            userRepository.createUser(user);
        }
        userRepository.updateUser(user);
        return user;
    }

    @DELETE
    @Path("{id}")
    public User deleteUser(@PathParam("id") int id){
        User u = userRepository.getUser(id);
        if(u.getId() != 0){
            userRepository.deleteUser(id);
        }

        return u;
    }
}
