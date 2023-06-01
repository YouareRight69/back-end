package fa.youareright.repository;

import fa.youareright.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,String> {

	@Query(value = "SELECT SUM(total) AS total FROM invoice ",nativeQuery =true)
	List<Total> getTotal();

	@Query(value="select count(*) as quantity, month(invoice_time) as month from invoice group by month(invoice_time) order by month asc" ,nativeQuery = true)
	List<Chart> getAll();

	@Query(value = "select   full_name,phone_number,invoice.user_id,total from user \r\n"
			+ "inner join invoice  on user.user_id = invoice.user_id  where  total  order by total desc limit 5 " ,nativeQuery = true)
	List<Total>getLimit();

	Page<Invoice> findAll(Specification<Invoice> spec, Pageable page);

	@Query(value="select inv from Invoice inv where inv.booking.bookingId = :bookigId")
	List<Invoice> checkExistInvoice(@Param("bookigId") String bookigId);

	@Query(value = "SELECT inv.invoiceId FROM Invoice inv WHERE inv.booking.bookingId = :bookingId")
	String findInvoiceIdByBookingId(@Param("bookingId") String bookingId);

	@Modifying
	@Query(value="update Invoice c set c.isDelete = 1 where c.invoiceId = :invoiceId")
	int deleteInvoice(@Param("invoiceId") String invoiceId );

	@Query(value = "SELECT inv FROM Invoice inv WHERE inv.booking.bookingId = :bookingId")
	List<Invoice> getInvoicesByBookingId(@Param("bookingId") String bookingId);

	@Modifying
	@Transactional
	@Query(value="update Invoice c set c.status = '1' where c.booking.bookingId = :bookingId")
	int thanhToanInvoice(@Param("bookingId") String bookingId );
}
