package gr.aueb.cf.springfinalproject.repository;

import gr.aueb.cf.springfinalproject.model.CourseSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface CourseSessionRepository extends JpaRepository<CourseSession, Long> {
    List<CourseSession> findByStartDateTimeBetween(LocalDateTime start, LocalDateTime end);

}
