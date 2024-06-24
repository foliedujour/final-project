package gr.aueb.cf.springfinalproject.dto;

import gr.aueb.cf.springfinalproject.model.Booking;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingResponseDTO {

    private BookingDTO bookingDTO;
    private boolean isSuccess;
    private String message;
}
