package fa.youareright.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AccountRoleId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String accountId;
    private String roleId;
}
