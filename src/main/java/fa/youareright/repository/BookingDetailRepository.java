package fa.youareright.repository;

import fa.youareright.model.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingDetailRepository extends JpaRepository<BookingDetail,String> {

    @Query(value="select bd from BookingDetail  bd where bd.booking.bookingDate = :day and bd.employee.employeeId = :employeeId")
    List<BookingDetail> getBusyTimeOfEmployee(@Param("day") LocalDate day, @Param("employeeId") String employeeId);
}
