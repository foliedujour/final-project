package gr.aueb.cf.springfinalproject.service.exceptions;

import java.io.Serial;

public class DoubleBookingException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DoubleBookingException(Long userId, Long courseSessionId) {
        super("User with id " + userId + " has already been booked for course session with id " + courseSessionId + ".");
    }
}
