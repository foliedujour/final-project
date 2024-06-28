package gr.aueb.cf.springfinalproject.controller;

import gr.aueb.cf.springfinalproject.model.Room;
import gr.aueb.cf.springfinalproject.service.RoomServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "Room", description = "Room Management APIs")
public class RoomController {

    private final RoomServiceImpl roomService;

    @GetMapping()
    @Operation(summary = "Retrieves all rooms", description = "Fetches a list of all available rooms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rooms retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Room.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
}
