package gr.aueb.cf.springfinalproject.controller;

import gr.aueb.cf.springfinalproject.authentication.JwtTokenProvider;
import gr.aueb.cf.springfinalproject.dto.LoginRequestDTO;
import gr.aueb.cf.springfinalproject.dto.LoginResponseDTO;
import gr.aueb.cf.springfinalproject.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public LoginResponseDTO authenticateUser(@RequestBody LoginRequestDTO loginRequestDTO) {
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
    public ResponseEntity<String> getWelcomeMessage(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7); // Remove "Bearer " prefix
        System.out.println("lalalala");
        String username = jwtTokenProvider.getUsernameFromToken(jwt);
        return ResponseEntity.ok(username);
    }
}