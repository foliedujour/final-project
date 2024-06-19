package gr.aueb.cf.springfinalproject.service.exceptions;

import java.io.Serial;

public class InstructorNotAvailableException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;


    public InstructorNotAvailableException(Long id) {
        super(String.format("Instructor with id %s is not available", id));
    }
}
