package fa.youareright.repository;

import fa.youareright.model.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDetailRepository extends JpaRepository<BookingDetail,String> {
}
