package gr.aueb.cf.springfinalproject.service.exceptions;

import java.io.Serial;

public class InstructorAlreadyExistException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public InstructorAlreadyExistException(String ssn) {
        super(String.format("Instructor with ssn %s already exists", ssn ));
    }
}
