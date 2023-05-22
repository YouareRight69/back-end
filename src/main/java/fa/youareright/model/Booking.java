package fa.youareright.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @Column(name = "booking_id", columnDefinition = "varchar(10)")
    private String bookingId;
    @Column(name = "booking_date",columnDefinition = "date")
    private LocalDate bookingDate;
    @Column(name ="is_delete",  columnDefinition = "int default 0")
    private int isDelete;
    @Column(columnDefinition = "text")
    private String note;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "booking")
    private List<BookingDetail> bookingDetailList;

    @OneToOne(mappedBy = "booking")
    private Invoice invoice;

     
}
