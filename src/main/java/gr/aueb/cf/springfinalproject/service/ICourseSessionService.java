package gr.aueb.cf.springfinalproject.service;

import gr.aueb.cf.springfinalproject.model.CourseSession;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ICourseSessionService {
    List<CourseSession> getAllCourseSessionsByWeek(LocalDateTime date);
    List<CourseSession> getAllCourseSessionsByMonth(LocalDateTime date);
}
