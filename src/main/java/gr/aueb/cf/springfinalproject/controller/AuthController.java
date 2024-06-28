package gr.aueb.cf.springfinalproject.controller;

import gr.aueb.cf.springfinalproject.authentication.JwtTokenProvider;
import gr.aueb.cf.springfinalproject.dto.LoginRequestDTO;
import gr.aueb.cf.springfinalproject.dto.LoginResponseDTO;
import gr.aueb.cf.springfinalproject.model.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication Management APIs")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    @Operation(summary = "Authenticates a user", description = "Authenticates a user with the provided username and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid username or password")
    })
    public LoginResponseDTO authenticateUser(
            @RequestBody LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        User user = (User) authentication.getPrincipal();
        String role = user.getRole().name();

        return new LoginResponseDTO(jwt, role);
    }

    @GetMapping("/welcome")
    @Operation(summary = "Returns a welcome message",
            description = "Returns a welcome message for the authenticated user retrieving also its username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Welcome message retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid or missing token")
    })
    public ResponseEntity<String> getWelcomeMessage(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7); // Remove "Bearer " prefix
        String username = jwtTokenProvider.getUsernameFromToken(jwt);
        return ResponseEntity.ok(username);
    }
}