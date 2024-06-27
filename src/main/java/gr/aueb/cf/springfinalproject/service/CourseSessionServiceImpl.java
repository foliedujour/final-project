package gr.aueb.cf.springfinalproject.service;

import gr.aueb.cf.springfinalproject.dto.CourseSessionDTO;
import gr.aueb.cf.springfinalproject.dto.CreateCourseSessionDTO;
import gr.aueb.cf.springfinalproject.model.Course;
import gr.aueb.cf.springfinalproject.model.CourseSession;
import gr.aueb.cf.springfinalproject.model.Instructor;
import gr.aueb.cf.springfinalproject.model.Room;
import gr.aueb.cf.springfinalproject.repository.CourseRepository;
import gr.aueb.cf.springfinalproject.repository.CourseSessionRepository;
import gr.aueb.cf.springfinalproject.repository.InstructorRepository;
import gr.aueb.cf.springfinalproject.repository.RoomRepository;
import gr.aueb.cf.springfinalproject.service.exceptions.CourseSessionNotFoundException;
import gr.aueb.cf.springfinalproject.service.exceptions.InstructorNotAvailableException;
import gr.aueb.cf.springfinalproject.service.exceptions.InstructorNotFoundException;
import gr.aueb.cf.springfinalproject.service.exceptions.RoomNotAvailableException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CourseSessionServiceImpl implements ICourseSessionService {


    private final CourseSessionRepository courseSessionRepository;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final RoomRepository roomRepository;


    @Override
    public List<CourseSession> getAllCourseSessions() {
        return courseSessionRepository.findAll();
    }

    @Override
    public List<CourseSessionDTO> getAllCourseSessionsByWeek(LocalDateTime date) {
        LocalDateTime startOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfWeek = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).withHour(23).withMinute(59).withSecond(59);
        List<CourseSession> courseSessions = courseSessionRepository.findByStartDateTimeBetween(startOfWeek, endOfWeek);


        return courseSessions.stream()
                .map(courseSession -> new CourseSessionDTO(
                        courseSession.getId(),
                        courseSession.getCourse().getTitle(),
                        courseSession.getCourse().getDescription(),
                        courseSession.getInstructor().getFirstname(),
                        courseSession.getStartDateTime(),
                        courseSession.getEndDateTime(),
                        courseSession.getRoom().getName()))
                .collect(Collectors.toList());
    }



    @Override
    public List<CourseSession> getAllCourseSessionsByMonth(LocalDateTime date) {

        LocalDateTime startOfMonth = date.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfMonth = date.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59);

        return courseSessionRepository.findByStartDateTimeBetween(startOfMonth, endOfMonth);
    }

    @Override
    public boolean isInstructorAvailable(Long instructorId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<CourseSession> overlappingSessions = courseSessionRepository.findOverlappingSessionsForInstructor(instructorId, startDateTime, endDateTime);

        return overlappingSessions.isEmpty();
    }

    public boolean isRoomAvailable(Long roomId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<CourseSession> overlappingSessions = courseSessionRepository.findOverlappingSessionsForRoom(roomId, startDateTime, endDateTime);
        return overlappingSessions.isEmpty();
    }

    @Override
    public CourseSession createCourseSession(CreateCourseSessionDTO createCourseSessionDTO) {
        Instructor instructor = instructorRepository.findById(createCourseSessionDTO.getInstructorId())
                .orElseThrow(() -> new InstructorNotFoundException(createCourseSessionDTO.getInstructorId()));


        Room room = roomRepository.findById(createCourseSessionDTO.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Course course = courseRepository.findById(createCourseSessionDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        LocalDateTime startDateTime = createCourseSessionDTO.getStartDateTime();

        LocalDateTime endDateTime = createCourseSessionDTO.getEndDateTime();

        boolean isInstructorAvailable = isInstructorAvailable(instructor.getId(), startDateTime, endDateTime);
        boolean isRoomAvailable = isRoomAvailable(room.getId(), startDateTime, endDateTime);

        if (!isInstructorAvailable) {
            throw new InstructorNotAvailableException(instructor.getId());
        }

        if (!isRoomAvailable) {
            throw new RoomNotAvailableException(room.getId());
        }

        CourseSession courseSession = new CourseSession();
        course.addSession(courseSession);
        instructor.addCourseSession(courseSession);
        room.addSession(courseSession);
        courseSession.setStartDateTime(startDateTime);
        courseSession.setEndDateTime(endDateTime);

        return courseSessionRepository.save(courseSession);
    }

    @Override
    public void deleteCourseSession(Long courseSessionId) {
        CourseSession courseSession = courseSessionRepository.findById(courseSessionId)
                .orElseThrow(() -> new CourseSessionNotFoundException(courseSessionId));
        courseSessionRepository.delete(courseSession);
    }

}
