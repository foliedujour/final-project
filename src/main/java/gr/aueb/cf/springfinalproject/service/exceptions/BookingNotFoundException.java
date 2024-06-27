package gr.aueb.cf.springfinalproject.service.exceptions;

import java.io.Serial;

public class BookingNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BookingNotFoundException(Long courseSessionId) {
        super("Booking for session with " + courseSessionId + " not found");
    }
}
