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
    @Column(columnDefinition = "int default 0")
    private int isDelete;

    @OneToMany(mappedBy = "branch")
    @JsonBackReference
    private List<Employee> employeeList;

    @OneToMany(mappedBy = "branch")
    private List<Media> media;
}
