package gr.aueb.cf.springfinalproject.controller;

import gr.aueb.cf.springfinalproject.dto.CourseSessionDTO;
import gr.aueb.cf.springfinalproject.model.CourseSession;
import gr.aueb.cf.springfinalproject.service.CourseSessionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Course Session", description = "Course Session Management APIs")
public class CourseSessionController {


    private final CourseSessionServiceImpl courseSessionService;

    @GetMapping()
    @Operation(summary = "Retrieves all course sessions", description = "Fetches a list of all available course sessions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course sessions retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseSession.class)))
    }) // Here I should return a dto, but it was too late to change
    public ResponseEntity<List<CourseSession>> getAllCourseSessions() {
        List<CourseSession> courseSessions = courseSessionService.getAllCourseSessions();
        return new ResponseEntity<>(courseSessions, HttpStatus.OK);
    }


    @GetMapping("/week")
    @Operation(summary = "Retrieves course sessions taking place in a week", description = "Fetches a list of course sessions for the specified week")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course sessions for the week retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CourseSessionDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid date format", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<CourseSessionDTO>> getCourseSessionsByWeek(
            @Parameter(description = "Date to get course sessions for the week", required = true)
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) java.time.LocalDateTime date) {
        List<CourseSessionDTO> courseSessions = courseSessionService.getAllCourseSessionsByWeek(date);
        return new ResponseEntity<>(courseSessions, HttpStatus.OK);
    }

    @GetMapping("/check-instructor-availability")
    @Operation(summary = "Checks instructor availability", description = "Checks if an instructor is available for the given time period")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructor availability checked successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters",
                    content = @Content(mediaType = "application/json"))
    })
    public boolean checkInstructorAvailability(
            @Parameter(description = "ID of the instructor", required = true)
            @RequestParam("instructorId") Long instructorId,
            @Parameter(description = "Start date and time for availability check", required = true)
            @RequestParam("startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @Parameter(description = "End date and time for availability check", required = true)
            @RequestParam("endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime
    ) {

        // Logging to debug
        System.out.println("Instructor ID: " + instructorId);
        System.out.println("Start DateTime: " + startDateTime);
        System.out.println("End DateTime: " + endDateTime);

        return courseSessionService.isInstructorAvailable(instructorId, startDateTime, endDateTime);
    }

    @GetMapping("/check-room-availability")
    @Operation(summary = "Checks room availability", description = "Checks if a room is available for the given time period")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room availability checked successfully",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters",
                    content = @Content(mediaType = "application/json"))
    })
    public boolean checkRoomAvailability(
            @Parameter(description = "ID of the room", required = true)
            @RequestParam Long roomId,
            @Parameter(description = "Start date and time for availability check", required = true)
            @RequestParam LocalDateTime startDateTime,
            @Parameter(description = "End date and time for availability check", required = true)
            @RequestParam LocalDateTime endDateTime
    ) {
        return courseSessionService.isRoomAvailable(roomId, startDateTime, endDateTime);
    }

}
