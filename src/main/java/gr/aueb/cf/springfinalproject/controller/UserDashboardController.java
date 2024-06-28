package gr.aueb.cf.springfinalproject.controller;

import gr.aueb.cf.springfinalproject.dto.BookingRequestDTO;
import gr.aueb.cf.springfinalproject.dto.BookingResponseDTO;
import gr.aueb.cf.springfinalproject.dto.CourseSessionDTO;
import gr.aueb.cf.springfinalproject.service.BookingServiceImpl;
import gr.aueb.cf.springfinalproject.service.exceptions.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User Dashboard", description = "User Dashboard Management APIs")
public class UserDashboardController {

    private final BookingServiceImpl bookingService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/course-sessions")
    @Operation(summary = "Retrieves user course sessions",
            description = "Fetches a list of course sessions booked by the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course sessions retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseSessionDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<CourseSessionDTO>> getUserCourseSessions(
            @Parameter(description = "ID of the user", required = true)
            @RequestParam Long userId) {
        List<CourseSessionDTO> courseSessions = bookingService.getUserCourseSessions(userId);
        return ResponseEntity.ok(courseSessions);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/bookings")
    @Operation(summary = "Books a session for the user",
            description = "Books a session for the user based on the provided booking request details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session booked successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookingResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Course session or user not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Booking conflict",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Room capacity exceeded or past session booking attempt",
                    content = @Content(mediaType = "application/json"))
    })
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
    @Operation(summary = "Unbooks a session for the user", description = "Unbooks a session for the user based on the provided booking request details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session unbooked successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookingResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Course session or user not found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Booking not found",
                    content = @Content(mediaType = "application/json"))
    })
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
