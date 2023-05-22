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
    @Column(columnDefinition = "int default 0")
    private int isDelete;
    private String type;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    @JsonBackReference
    private Branch branch;

    @OneToMany(mappedBy = "employee")
    private List<BookingDetail> bookingDetailList;

    @OneToMany(mappedBy = "employee")
    private List<InvoiceDetail> invoiceDetailList;
}
