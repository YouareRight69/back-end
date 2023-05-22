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

    @Column(columnDefinition = "varchar(45)")
    private String email;
    @JsonBackReference
    @OneToOne(mappedBy = "account")
    private User user;

    @OneToMany (mappedBy = "account")
    private List<AccountRole> accountRoleList;
}
