package gr.aueb.cf.springfinalproject.dto;

import gr.aueb.cf.springfinalproject.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private Role role;
}
