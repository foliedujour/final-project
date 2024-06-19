package gr.aueb.cf.springfinalproject.repository;

import gr.aueb.cf.springfinalproject.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    Optional<Instructor> findBySsn(String ssn);
}
