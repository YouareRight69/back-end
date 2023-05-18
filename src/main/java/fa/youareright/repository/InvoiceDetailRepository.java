package fa.youareright.repository;

import fa.youareright.model.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDetailRepository  extends JpaRepository<InvoiceDetail,String> {
}
