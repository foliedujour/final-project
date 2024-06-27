package gr.aueb.cf.springfinalproject.service;

import gr.aueb.cf.springfinalproject.dto.*;
import gr.aueb.cf.springfinalproject.model.Booking;
import gr.aueb.cf.springfinalproject.model.CourseSession;
import gr.aueb.cf.springfinalproject.model.User;
import gr.aueb.cf.springfinalproject.repository.BookingRepository;
import gr.aueb.cf.springfinalproject.repository.CourseSessionRepository;
import gr.aueb.cf.springfinalproject.repository.UserRepository;
import gr.aueb.cf.springfinalproject.service.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookingServiceImpl implements IBookingService {

    private final BookingRepository bookingRepository;
    private final CourseSessionRepository courseSessionRepository;
    private final UserRepository userRepository;

    @Override
    public BookingResponseDTO bookSession(BookingRequestDTO bookingRequestDTO)
            throws CourseSessionNotFoundException, UserNotFoundException,
            BookingConflictException, RoomCapacityExceededException, BookPastSessionException {
        CourseSession courseSession = courseSessionRepository.findById(bookingRequestDTO.getCourseSessionId())
                .orElseThrow(() -> new CourseSessionNotFoundException(bookingRequestDTO.getCourseSessionId()));

        User user = userRepository.findById(bookingRequestDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException(bookingRequestDTO.getUserId()));

        Booking existingBooking = bookingRepository.findUserBookingForSession(user.getId(), courseSession.getId());
        if (existingBooking != null) {
            throw new BookingConflictException("User has already booked fot this session.");
        }

        if (courseSession.getStartDateTime().isBefore(LocalDateTime.now())) {
            throw new BookPastSessionException(courseSession.getId());
        }

        Set<Booking> bookingsForCourseSession = courseSession.fetchAllBookings();

        if (bookingsForCourseSession.size() >= courseSession.getRoom().getCapacity()) {
            throw new RoomCapacityExceededException(courseSession.getId());
        }

        Booking booking = new Booking();
        user.addBooking(booking);
        courseSession.addBooking(booking);
        booking.setStartTime(courseSession.getStartDateTime());
        booking.setEndTime(courseSession.getEndDateTime());
        bookingRepository.save(booking);

        UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getRole());
        BookingDTO bookingDTO = new BookingDTO(booking.getId(), courseSession.getId(),
                booking.getStartTime(), booking.getEndTime(), userDTO);
        return new BookingResponseDTO(bookingDTO, true, "Booking successful");
    }

    @Override
    public BookingResponseDTO unBookSession(BookingRequestDTO bookingRequestDTO) throws CourseSessionNotFoundException, UserNotFoundException, BookingNotFoundException {
        CourseSession session = courseSessionRepository.findById(bookingRequestDTO.getCourseSessionId())
                .orElseThrow(() -> new CourseSessionNotFoundException(bookingRequestDTO.getCourseSessionId()));

        User user = userRepository.findById(bookingRequestDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException(bookingRequestDTO.getUserId()));


        Booking existingBooking = bookingRepository.findUserBookingForSession(user.getId(), session.getId());
        if (existingBooking == null) {
            throw new BookingNotFoundException(session.getId());
        }

        user.removeBooking(existingBooking);
        session.removeBooking(existingBooking);
        bookingRepository.delete(existingBooking);

        UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getRole());
        BookingDTO bookingDTO = new BookingDTO(existingBooking.getId(), session.getId(),
                existingBooking.getStartTime(), existingBooking.getEndTime(), userDTO);

        return new BookingResponseDTO(bookingDTO, true, "You successfully unbooked for this session");
    }


    @Override
    public List<CourseSessionDTO> getUserCourseSessions(Long userId) throws CourseSessionNotFoundException, UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return user.fetchAllBookings().stream()
                .map(Booking::getCourseSession)
                .map(courseSession -> new CourseSessionDTO(
                        courseSession.getId(),
                        courseSession.getCourse().getTitle(),
                        courseSession.getCourse().getDescription(),
                        courseSession.getInstructor().getFirstname(),
                        courseSession.getStartDateTime(),
                        courseSession.getEndDateTime(),
                        courseSession.getRoom().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isUserBookedForSession(Long userId, Long sessionId) {
        return bookingRepository.existsByUserIdAndCourseSessionId(userId, sessionId);
    }
}
