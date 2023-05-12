package fa.youareright.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Account {
    @Id
    @Column(name = "account_id", columnDefinition = "varchar(10)")
    private String accountId;
    @Column(name = "username", columnDefinition = "varchar(30)")
    private String userName;
    @Column(name = "password", columnDefinition = "varchar(100)")
    private String password;

    @OneToOne(mappedBy = "account")
    private User user;

    @OneToMany (mappedBy = "account")
    private List<AccountRole> accountRoleList;
}
