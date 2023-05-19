package fa.youareright.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetail {
    @Id
    @Column(name = "invoice_detail_id", columnDefinition = "varchar(10)")
    private String invoiceDetailId;
    @Column(columnDefinition = "int default 0")
    private int isDelete;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "service_id")
    private HairService hairService;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
