package fa.youareright.model;

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
    @JoinColumn(name = "service_id")
    private HairService hairService;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
