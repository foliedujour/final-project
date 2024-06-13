package gr.aueb.cf.springfinalproject.controller;

import gr.aueb.cf.springfinalproject.authentication.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController("/admin")
@RequiredArgsConstructor
public class AdminDashboardController {

//    private final JwtTokenProvider jwtTokenProvider;
//
//    private ResponseEntity<String> loadUsername(@RequestHeader("Authorization") String token) {
//        String jwt = token.substring(7);
//        String username = jwtTokenProvider.getUsernameFromToken(jwt);
//        return ResponseEntity.ok(username);
//    }
}
