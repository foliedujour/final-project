package gr.aueb.cf.springfinalproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
public class RegisterInstructorDTO {

    @NotNull(message = "First name is required")
    @Size(min = 3, max = 32)
    private String firstname;

    @NotNull(message = "Last name is required")
    @Size(min = 3, max = 32)
    private String lastname;

    @NotNull(message = "SSN is required")
    @Size(min = 5, max = 5, message = "SSN must be exactly 5 digits")
    private String ssn;
}
