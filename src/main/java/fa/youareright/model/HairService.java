package fa.youareright.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HairService {
    @Id
    @Column(name = "service_id",columnDefinition = "varchar(10)")
    private String serviceId;
    private String name;
    private float price;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    private String type;
    @Column(columnDefinition = "int default 0")
    private int isDelete;

    @OneToMany(mappedBy = "hairService")
    @JsonBackReference
    private List<BookingDetail> bookingDetailList;

    @OneToMany(mappedBy = "hairService")
    private List<InvoiceDetail> invoiceDetailList;

    @OneToMany(mappedBy = "hairService")
    private List<Media> media;
}
