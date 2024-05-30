package gr.aueb.cf.springfinalproject.repository;

import gr.aueb.cf.springfinalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
