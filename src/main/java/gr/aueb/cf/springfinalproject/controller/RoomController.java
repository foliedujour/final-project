package gr.aueb.cf.springfinalproject.controller;

import gr.aueb.cf.springfinalproject.model.Room;
import gr.aueb.cf.springfinalproject.service.RoomServiceImpl;
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
public class RoomController {

    private final RoomServiceImpl roomService;

    @GetMapping()
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
}
