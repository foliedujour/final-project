package gr.aueb.cf.springfinalproject.service;

import gr.aueb.cf.springfinalproject.dto.BookingRequestDTO;
import gr.aueb.cf.springfinalproject.dto.BookingResponseDTO;
import gr.aueb.cf.springfinalproject.dto.CourseSessionDTO;
import gr.aueb.cf.springfinalproject.service.exceptions.*;

import java.util.List;

public interface IBookingService {
    BookingResponseDTO bookSession(BookingRequestDTO bookingRequestDTO)
            throws CourseSessionNotFoundException, UserNotFoundException,
            BookingConflictException, RoomCapacityExceededException,
            BookPastSessionException;

    BookingResponseDTO unBookSession(BookingRequestDTO bookingRequestDTO)
            throws CourseSessionNotFoundException, UserNotFoundException,
            BookingNotFoundException;

    List<CourseSessionDTO> getUserCourseSessions(Long userId) throws
            CourseSessionNotFoundException, UserNotFoundException;

    boolean isUserBookedForSession(Long userId, Long sessionId);
}
