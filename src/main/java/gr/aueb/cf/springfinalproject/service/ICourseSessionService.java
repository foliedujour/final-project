package gr.aueb.cf.springfinalproject.service;

import gr.aueb.cf.springfinalproject.dto.CreateCourseSessionDTO;
import gr.aueb.cf.springfinalproject.model.CourseSession;

import java.time.LocalDateTime;
import java.util.List;

public interface ICourseSessionService {
    List<CourseSession> getAllCourseSessions();
    List<CourseSession> getAllCourseSessionsByWeek(LocalDateTime date);
    List<CourseSession> getAllCourseSessionsByMonth(LocalDateTime date);
    boolean isInstructorAvailable(Long instructorId, LocalDateTime startDateTime, LocalDateTime endDateTime);
    boolean isRoomAvailable(Long roomId, LocalDateTime startDateTime, LocalDateTime endDateTime);
    CourseSession createCourseSession(CreateCourseSessionDTO createCourseSessionDTO);
}

