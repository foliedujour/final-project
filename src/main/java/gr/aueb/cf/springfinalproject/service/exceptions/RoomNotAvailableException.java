package gr.aueb.cf.springfinalproject.service.exceptions;

import java.io.Serial;

public class RoomNotAvailableException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public RoomNotAvailableException(Long id) {
        super(String.format("Room with id %d is not available", id));
    }
}
