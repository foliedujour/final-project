package gr.aueb.cf.springfinalproject.controller;

import gr.aueb.cf.springfinalproject.model.Instructor;
import gr.aueb.cf.springfinalproject.service.InstructorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "Instructor", description = "Instructor Management APIs")
public class InstructorController {

    private final InstructorServiceImpl instructorService;

    @GetMapping
    @Operation(summary = "Retrieves all instructors", description = "Fetches a list of all available instructors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Instructors retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Instructor.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        List<Instructor> instructors = instructorService.getAllInstructors();
        return ResponseEntity.ok(instructors);
    }
}
