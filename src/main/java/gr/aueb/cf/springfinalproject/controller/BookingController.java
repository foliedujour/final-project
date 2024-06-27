package gr.aueb.cf.springfinalproject.controller;

import gr.aueb.cf.springfinalproject.service.BookingServiceImpl;
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

public class BookingController {


    private final BookingServiceImpl bookingService;

    @GetMapping("/isBooked")

    public ResponseEntity<Boolean> isBooked(

            @RequestParam Long userId, @RequestParam  Long sessionId) {
        boolean isBooked = bookingService.isUserBookedForSession(userId, sessionId);
        return ResponseEntity.ok(isBooked);
    }
}
