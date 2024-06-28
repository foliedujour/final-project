package gr.aueb.cf.springfinalproject.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rooms")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Room extends AbstractEntity {

    private String name;

    private int capacity;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter(AccessLevel.PROTECTED)
    private Set<CourseSession> sessions = new HashSet<>();

    public Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.sessions = new HashSet<>();
    }

    public void addSession(CourseSession session) {
        sessions.add(session);
        session.setRoom(this);
    }
}
