package gr.aueb.cf.springfinalproject.service.exceptions;

import java.io.Serial;

public class CourseSessionNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CourseSessionNotFoundException(Long id) {
        super("Course session with id " + id + " not found");
    }
}
