package gr.aueb.cf.springfinalproject.service.exceptions;

import java.io.Serial;

public class RoomCapacityExceededException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RoomCapacityExceededException(Long id) {
        super("There are no slot available for lesson with id " + id);
    }
}
