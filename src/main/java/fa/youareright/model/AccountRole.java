package fa.youareright.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AccountRole {
    @EmbeddedId
    @Column(name = "account_role_id", columnDefinition = "varchar(10)")
    private AccountRoleId id;

    @ManyToOne
    @JoinColumn(name="account_id",columnDefinition = "varchar(10)")
    @MapsId("accountId")
    private Account account;

    @ManyToOne
    @JoinColumn(name="role_id",columnDefinition = "varchar(10)")
    @MapsId("roleId")
    private Role role;
}
