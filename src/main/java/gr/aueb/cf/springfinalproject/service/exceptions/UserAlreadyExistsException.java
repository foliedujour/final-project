package gr.aueb.cf.springfinalproject.service.exceptions;

import java.io.Serial;

public class UserAlreadyExistsException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException(String username) {
        super("User with username " + username + " already exists.");
    }
}
