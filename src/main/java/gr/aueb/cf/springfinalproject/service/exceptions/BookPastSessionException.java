package gr.aueb.cf.springfinalproject.service.exceptions;

import java.io.Serial;

public class BookPastSessionException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BookPastSessionException(Long sessionId) {
        super("You cannot book past session with id " + sessionId);
    }
}
