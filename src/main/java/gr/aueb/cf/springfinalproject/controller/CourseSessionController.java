package gr.aueb.cf.springfinalproject.controller;

import gr.aueb.cf.springfinalproject.model.CourseSession;
import gr.aueb.cf.springfinalproject.service.CourseSessionServiceImpl;
import gr.aueb.cf.springfinalproject.service.exceptions.InstructorNotAvailableException;
import gr.aueb.cf.springfinalproject.service.exceptions.RoomNotAvailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/course-sessions")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CourseSessionController {


    private final CourseSessionServiceImpl courseSessionService;

    @GetMapping()
    public ResponseEntity<List<CourseSession>> getAllCourseSessions() {
        List<CourseSession> courseSessions = courseSessionService.getAllCourseSessions();
        return new ResponseEntity<>(courseSessions, HttpStatus.OK);
    }

    @GetMapping("/week")
    public ResponseEntity<List<CourseSession>> getCourseSessionsByWeek(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) java.time.LocalDateTime date) {
        List<CourseSession> courseSessions = courseSessionService.getAllCourseSessionsByWeek(date);
        return new ResponseEntity<>(courseSessions, HttpStatus.OK);
    }

    @GetMapping("/check-instructor-availability")
    public boolean checkInstructorAvailability(
            @RequestParam("instructorId") Long instructorId,
            @RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime
    ) {

        // Logging to debug
        System.out.println("Instructor ID: " + instructorId);
        System.out.println("Start DateTime: " + startDateTime);
        System.out.println("End DateTime: " + endDateTime);

        return courseSessionService.isInstructorAvailable(instructorId, startDateTime, endDateTime);
    }

    @GetMapping("/check-room-availability")
    public boolean checkRoomAvailability(
            @RequestParam Long roomId,
            @RequestParam LocalDateTime startDateTime,
            @RequestParam LocalDateTime endDateTime
    ) {
        return courseSessionService.isRoomAvailable(roomId, startDateTime, endDateTime);
    }

}
