package gr.aueb.cf.springfinalproject.service;

import gr.aueb.cf.springfinalproject.model.CourseSession;
import gr.aueb.cf.springfinalproject.repository.CourseSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

@Service
public class CourseSessionServiceImpl implements ICourseSessionService {

    @Autowired
    private CourseSessionRepository courseSessionRepository;

    @Override
    public List<CourseSession> getAllCourseSessionsByWeek(LocalDateTime date) {
        LocalDateTime startOfWeek = date.with(TemporalAdjusters.previousOrSame(LocalDate.now().getDayOfWeek())).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfWeek = date.with(TemporalAdjusters.nextOrSame(LocalDate.now().plusDays(6).getDayOfWeek())).withHour(23).withMinute(59).withSecond(59);

        return courseSessionRepository.findByStartDateTimeBetween(startOfWeek, endOfWeek);
    }

    @Override
    public List<CourseSession> getAllCourseSessionsByMonth(LocalDateTime date) {

        LocalDateTime startOfMonth = date.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfMonth = date.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59);

        return courseSessionRepository.findByStartDateTimeBetween(startOfMonth, endOfMonth);
    }
}
