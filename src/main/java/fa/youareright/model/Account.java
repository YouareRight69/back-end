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

    @JsonBackReference
    @OneToMany (mappedBy = "account")
    private List<AccountRole> accountRoleList;
}
