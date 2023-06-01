package fa.youareright.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.youareright.model.Invoice;
import fa.youareright.model.User;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,String> {
	
	
	@Query(value = "SELECT SUM(total) AS total FROM invoice  where status = '1' ",nativeQuery =true)
	List<Total> getTotal();
	// chart
	
	@Query(value="select sum(total) as quantity, month(invoice_time) as month from invoice group by month(invoice_time) order by month asc" ,nativeQuery = true)
	List<Chart> getAll();
	
	
	@Query(value = "select   full_name,sum(total) as 'total' ,invoice.user_id from user \r\n"
			+ "inner join invoice  on user.user_id = invoice.user_id \r\n"
			+ " where  total  group by full_name,invoice.user_id  order by total desc limit 5 " ,nativeQuery = true)
	List<Total>getLimit();
	
	
	@Query(value= "select sum( total)as quantity, month(invoice_time) as month ,"
			+ " branch.branch_id from invoice  inner join booking  on invoice.booking_id = "
			+ "booking.booking_id inner join branch  on booking.branch_id = branch.branch_id "
			+ "where branch.branch_id = ?1 group by month(invoice_time) order by month asc",nativeQuery = true)	
	List<Chart> getByChart( String branch);
	
	
	@Query(value="select sum(total) as quantity, month(invoice_time) as month "
			+ "from invoice "
			+ "where month(invoice_time) <= :endDate and month(invoice_time) >= :startDay "
			+ "group by month(invoice_time) "
			+ "order by month asc " ,nativeQuery = true)
	List<Chart> getByDay(@Param("startDay")String startDay,@Param("endDate") String endDate);
			    
}
