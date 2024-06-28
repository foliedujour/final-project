package gr.aueb.cf.springfinalproject.controller;

import gr.aueb.cf.springfinalproject.service.BookingServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/bookings")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "Booking", description = "Booking Management APIs")
public class BookingController {


    private final BookingServiceImpl bookingService;

    @GetMapping("/isBooked")
    @Operation(summary = "Checks if a user is booked for a session", description = "Returns a boolean indicating if the user is booked for the specified session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User booking status retrieved successfully", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid user ID or session ID", content = {@io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")})
    })
    public ResponseEntity<Boolean> isBooked(
            @Parameter(description = "ID of the user", required = true)
            @RequestParam Long userId,
            @Parameter(description = "ID of the session", required = true)
            @RequestParam  Long sessionId) {
        boolean isBooked = bookingService.isUserBookedForSession(userId, sessionId);
        return ResponseEntity.ok(isBooked);
    }
}
