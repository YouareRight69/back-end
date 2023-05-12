package fa.youareright.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class HairService {
    @Id
    @Column(name = "service_id",columnDefinition = "varchar(10)")
    private String serviceId;
    private String name;
    private float price;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    private String type;
    private String image;

    @OneToMany(mappedBy = "hairService")
    private List<BookingDetail> bookingDetailList;

    @OneToMany(mappedBy = "hairService")
    private List<InvoiceDetail> invoiceDetailList;
}
