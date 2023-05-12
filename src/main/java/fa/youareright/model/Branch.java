package fa.youareright.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Branch {
    @Id
    @Column(name="branch_id",columnDefinition = "varchar(10)")
    private String branchId;
    private String name;
    private String address;
    private String image;

    @OneToMany(mappedBy = "branch")
    private List<Employee> employeeList;
}
