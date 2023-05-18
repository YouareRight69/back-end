package fa.youareright.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @Column(name = "emp_id",columnDefinition = "varchar(10)")
    private  String employeeId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @OneToMany(mappedBy = "employee")
    @JsonBackReference
    private List<BookingDetail> bookingDetailList;

    @OneToMany(mappedBy = "employee")
    @JsonBackReference
    private List<InvoiceDetail> invoiceDetailList;
}
