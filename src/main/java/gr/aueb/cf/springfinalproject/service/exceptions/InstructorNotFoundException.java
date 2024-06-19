package gr.aueb.cf.springfinalproject.service.exceptions;

import java.io.Serial;

public class InstructorNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InstructorNotFoundException(Long id) {
        super(String.format("Instructor with %d not found", id));
    }
}
