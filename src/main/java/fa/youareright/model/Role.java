package fa.youareright.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @Column(name = "role_id", columnDefinition = "varchar(10)")
    private String roleId;
    private String name;

    @OneToMany(mappedBy = "role")
    private List<AccountRole> accountRoleList;
}
