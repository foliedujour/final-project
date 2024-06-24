package gr.aueb.cf.springfinalproject.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity implements UserDetails {


    @Column(unique = true, nullable = false)
    private String username;


    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter(AccessLevel.PROTECTED)
    private Set<Booking> bookings = new HashSet<>();

    public User(Long id, String username, String password, Role role) {
        this.setId(id);
        this.username = username;
        this.password = password;
        this.setRole(role);
        this.bookings = new HashSet<>();
    }

    public Set<Booking> fetchAllBookings() {
        return Collections.unmodifiableSet(bookings);
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.setUser(this);
    }

    public void removeBooking(Booking booking) {
        this.bookings.remove(booking);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
