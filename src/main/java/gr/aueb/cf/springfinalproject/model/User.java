package gr.aueb.cf.springfinalproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User extends AbstractEntity {

    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    private String email;
    private String username;
    private String password;



}
