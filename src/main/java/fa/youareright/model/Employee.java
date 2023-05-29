package fa.youareright.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
//    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "branch_id")
//    @JsonBackReference
    private Branch branch;

    @OneToMany(mappedBy = "employee")
    @JsonBackReference
    private List<BookingDetail> bookingDetailList;

    @OneToMany(mappedBy = "employee")
    private List<InvoiceDetail> invoiceDetailList;
}
