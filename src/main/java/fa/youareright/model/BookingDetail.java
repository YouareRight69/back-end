package fa.youareright.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetail {
    @Id
    @Column(name = "booking_detail_id",columnDefinition = "varchar(10)")
    private String bookingDetailId;
    private String name;
    @Column(columnDefinition = "int default 0")
    private int isDelete;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "service_id")
    private HairService hairService;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "emp_id")
    private Employee employee;

}
