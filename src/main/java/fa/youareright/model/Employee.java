package fa.youareright.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
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
    private List<BookingDetail> bookingDetailList;

    @OneToMany(mappedBy = "employee")
    private List<InvoiceDetail> invoiceDetailList;
}
