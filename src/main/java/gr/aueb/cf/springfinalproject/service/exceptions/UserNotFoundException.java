package gr.aueb.cf.springfinalproject.service.exceptions;

import gr.aueb.cf.springfinalproject.model.User;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Long id) {
        super("User with id " + id + " not found.");
    }


}
