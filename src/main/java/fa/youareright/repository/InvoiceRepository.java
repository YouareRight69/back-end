package fa.youareright.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fa.youareright.model.Invoice;
import fa.youareright.model.User;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,String> {
	
	
	@Query(value = "SELECT SUM(total) AS total FROM invoice ",nativeQuery =true)
	List<Total> getTotal();
	
	
	@Query(value="select count(*) as quantity, month(invoice_time) as month from invoice group by month(invoice_time) order by month asc" ,nativeQuery = true)
	List<Chart> getAll();
	
	
	@Query(value = "select   full_name,phone_number,invoice.user_id,total from user \r\n"
			+ "inner join invoice  on user.user_id = invoice.user_id  where  total  order by total desc limit 5 " ,nativeQuery = true)
	List<Total>getLimit();
	
	
	
	    
}
