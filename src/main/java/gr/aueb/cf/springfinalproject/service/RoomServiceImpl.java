package gr.aueb.cf.springfinalproject.service;

import gr.aueb.cf.springfinalproject.model.Room;
import gr.aueb.cf.springfinalproject.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomServiceImpl implements IRoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
