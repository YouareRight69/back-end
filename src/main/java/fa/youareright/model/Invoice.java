package fa.youareright.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    @Column(name = "invoice_id",columnDefinition = "varchar(10)")
    private String invoiceId;
    @Column(columnDefinition = "int default 0")
    private int isDelete;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @OneToMany(mappedBy = "invoice")
    @JsonBackReference
    private List<InvoiceDetail> invoiceDetailList;

    @Column(name = "invoice_time",columnDefinition = "DateTime")
    private LocalDateTime invoiceTime;

    private float total;
    private String status;


}
