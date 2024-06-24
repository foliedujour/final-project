package gr.aueb.cf.springfinalproject.service.exceptions;

public class BookingConflictException extends RuntimeException {

    public BookingConflictException(String message) {
        super(message);
    }
}
