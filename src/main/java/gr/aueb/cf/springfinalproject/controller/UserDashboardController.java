package gr.aueb.cf.springfinalproject.controller;

import gr.aueb.cf.springfinalproject.dto.BookingRequestDTO;
import gr.aueb.cf.springfinalproject.dto.BookingResponseDTO;
import gr.aueb.cf.springfinalproject.dto.CourseSessionDTO;
import gr.aueb.cf.springfinalproject.service.BookingServiceImpl;
import gr.aueb.cf.springfinalproject.service.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDashboardController {

    private final BookingServiceImpl bookingService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/course-sessions")
    public ResponseEntity<List<CourseSessionDTO>> getUserCourseSessions(@RequestParam Long userId) {
        List<CourseSessionDTO> courseSessions = bookingService.getUserCourseSessions(userId);
        return ResponseEntity.ok(courseSessions);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/bookings")
    public ResponseEntity<BookingResponseDTO> bookSession(@RequestBody BookingRequestDTO requestDTO) {
        try {
            BookingResponseDTO responseDTO = bookingService.bookSession(requestDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (CourseSessionNotFoundException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BookingResponseDTO(null, false, e.getMessage()));
        } catch (BookingConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new BookingResponseDTO(null, false, e.getMessage()));
        } catch (RoomCapacityExceededException | BookPastSessionException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BookingResponseDTO(null, false, e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/bookings")
    public ResponseEntity<BookingResponseDTO> unBookSession(@RequestBody BookingRequestDTO requestDTO) {
        try {
            BookingResponseDTO responseDTO = bookingService.unBookSession(requestDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (CourseSessionNotFoundException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BookingResponseDTO(null, false, e.getMessage()));
        } catch (BookingNotFoundException e1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BookingResponseDTO(null, false, e1.getMessage()));
        }
    }

}
