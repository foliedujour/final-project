package gr.aueb.cf.springfinalproject.repository;

import gr.aueb.cf.springfinalproject.model.CourseSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface CourseSessionRepository extends JpaRepository<CourseSession, Long> {


    List<CourseSession> findByStartDateTimeBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT cs FROM CourseSession cs WHERE cs.instructor.id = :instructorId AND ((cs.startDateTime <= :startDateTime AND cs.endDateTime > :startDateTime) OR (cs.startDateTime < :endDateTime AND cs.endDateTime >= :endDateTime) OR (cs.startDateTime >= :startDateTime AND cs.endDateTime <= :endDateTime))")
    List<CourseSession> findOverlappingSessionsForInstructor(
            @Param("instructorId") Long instructorId,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime
    );

    @Query("SELECT cs FROM CourseSession cs WHERE cs.room.id = :roomId AND ((cs.startDateTime <= :startDateTime AND cs.endDateTime > :startDateTime) OR (cs.startDateTime < :endDateTime AND cs.endDateTime >= :endDateTime) OR (cs.startDateTime >= :startDateTime AND cs.endDateTime <= :endDateTime))")
    List<CourseSession> findOverlappingSessionsForRoom(
            @Param("roomId") Long roomId,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime
    );

}
