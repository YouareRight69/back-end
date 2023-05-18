package fa.youareright.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRole {
    @EmbeddedId
    @Column(name = "account_role_id", columnDefinition = "varchar(10)")
    private AccountRoleId id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="account_id",columnDefinition = "varchar(10)")
    @MapsId("accountId")
    private Account account;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="role_id",columnDefinition = "varchar(10)")
    @MapsId("roleId")
    private Role role;
}
