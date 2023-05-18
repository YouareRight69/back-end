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
    @Column(name = "booking_time",columnDefinition = "Time")
    private LocalTime bookingTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "booking")
    @JsonBackReference
    private List<BookingDetail> bookingDetailList;

    @OneToOne(mappedBy = "booking")
    @JsonBackReference
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "working_time_id")
    private WorkingTime workingTime;
     
}
