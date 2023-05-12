package fa.youareright.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class InvoiceDetail {
    @Id
    @Column(name = "invoice_detail_id", columnDefinition = "varchar(10)")
    private String invoiceDetailId;

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
