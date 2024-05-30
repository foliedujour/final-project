package gr.aueb.cf.springfinalproject.repository;

import gr.aueb.cf.springfinalproject.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
