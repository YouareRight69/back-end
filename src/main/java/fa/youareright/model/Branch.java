package fa.youareright.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    @Id
    @Column(name="branch_id",columnDefinition = "varchar(10)")
    private String branchId;
    private String name;
    private String address;
    private String image;

    @OneToMany(mappedBy = "branch")
    @JsonBackReference
    private List<Employee> employeeList;
}
