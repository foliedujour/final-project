package gr.aueb.cf.springfinalproject.service.exceptions;

import gr.aueb.cf.springfinalproject.model.User;

import java.io.Serial;

public class UserNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(User user) {
        super("User with id " + user.getId() + " not found.");
    }


}
