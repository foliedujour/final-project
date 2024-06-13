package gr.aueb.cf.springfinalproject.repository;

import gr.aueb.cf.springfinalproject.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
