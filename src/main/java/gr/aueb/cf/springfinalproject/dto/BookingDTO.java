package gr.aueb.cf.springfinalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private Long id;
    private Long sessionId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private UserDTO userDTO;
}