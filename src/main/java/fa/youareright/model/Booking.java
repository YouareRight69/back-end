package fa.youareright.model;

import javax.persistence.*;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
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
    private List<BookingDetail> bookingDetailList;

    @OneToOne(mappedBy = "booking")
    private Invoice invoice;
     
}
