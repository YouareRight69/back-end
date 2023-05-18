package fa.youareright.repository;

import fa.youareright.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository  extends JpaRepository<Booking, String> {
}
