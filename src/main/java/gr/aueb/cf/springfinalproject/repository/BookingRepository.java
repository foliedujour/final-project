package gr.aueb.cf.springfinalproject.repository;

import gr.aueb.cf.springfinalproject.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.user.id = :userId AND " +
            "b.courseSession.startDateTime < :end AND " +
            "b.courseSession.endDateTime > :start")
    List<Booking> findOverlappingBookings(@Param("userId") String userId,
                                          @Param("start") LocalDateTime start,
                                          @Param("end") LocalDateTime end);



    @Query("SELECT b FROM Booking b WHERE b.user.id = :userId AND b.courseSession.id = :sessionId")
    Booking findUserBookingForSession(@Param("userId") Long userId, @Param("sessionId") Long sessionId);

    List<Booking> findByUserId(Long userId);
    boolean existsByUserIdAndCourseSessionId(Long userId, Long courseSessionId);
}
