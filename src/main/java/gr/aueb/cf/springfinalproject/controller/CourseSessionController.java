package gr.aueb.cf.springfinalproject.controller;

import gr.aueb.cf.springfinalproject.model.CourseSession;
import gr.aueb.cf.springfinalproject.service.CourseSessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course-sessions")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseSessionController {

    @Autowired
    private CourseSessionServiceImpl courseSessionService;

    @GetMapping("/week")
    public ResponseEntity<List<CourseSession>> getCourseSessionsByWeek(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) java.time.LocalDateTime date) {
        List<CourseSession> courseSessions = courseSessionService.getAllCourseSessionsByWeek(date);
        return new ResponseEntity<>(courseSessions, HttpStatus.OK);}
}
