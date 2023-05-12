package fa.youareright.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Invoice {
    @Id
    @Column(name = "invoice_id",columnDefinition = "varchar(10)")
    private String invoiceId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceDetail> invoiceDetailList;

    @Column(name = "invoice_time",columnDefinition = "DateTime")
    private LocalDateTime invoiceTime;

    private float total;
    private String status;


}
