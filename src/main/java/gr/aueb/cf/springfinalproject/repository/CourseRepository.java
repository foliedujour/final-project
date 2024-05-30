package gr.aueb.cf.springfinalproject.repository;

import gr.aueb.cf.springfinalproject.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
