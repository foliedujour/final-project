package gr.aueb.cf.springfinalproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingRequestDTO {

    @NotNull
    private Long courseSessionId;

    @NotNull
    private Long userId;

}
