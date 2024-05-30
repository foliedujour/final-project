package gr.aueb.cf.springfinalproject.repository;

import gr.aueb.cf.springfinalproject.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
