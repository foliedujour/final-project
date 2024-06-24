package gr.aueb.cf.springfinalproject.service;

import gr.aueb.cf.springfinalproject.dto.BookingRequestDTO;
import gr.aueb.cf.springfinalproject.dto.BookingResponseDTO;
import gr.aueb.cf.springfinalproject.dto.CourseSessionDTO;
import gr.aueb.cf.springfinalproject.model.Booking;
import gr.aueb.cf.springfinalproject.service.exceptions.BookingConflictException;
import gr.aueb.cf.springfinalproject.service.exceptions.CourseSessionNotFoundException;
import gr.aueb.cf.springfinalproject.service.exceptions.RoomCapacityExceededException;
import gr.aueb.cf.springfinalproject.service.exceptions.UserNotFoundException;

import java.util.List;

public interface IBookingService {
    BookingResponseDTO bookSession(BookingRequestDTO bookingRequestDTO)
            throws CourseSessionNotFoundException, UserNotFoundException,
            BookingConflictException, RoomCapacityExceededException;

    List<CourseSessionDTO> getUserCourseSessions(Long userId) throws
            CourseSessionNotFoundException, UserNotFoundException;

    boolean isUserBookedForSession(Long userId, Long sessionId);
}
