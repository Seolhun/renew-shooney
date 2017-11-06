package hi.cord.com.jpa.user.domain.privilege;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;

/**
 * The type User profile.
 *
 * @link {UserProfileType}
 */
@Entity(name = "TB_PRIVILEGE")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Getter
@Setter
public class ProfilePrivilege implements Serializable {
    private static final long serialVersionUID = 3788477180129427170L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRIVILEGE_ID")
    private long id;

    @Column(name = "PRIVILEGE_TYPE", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfilePrivilegeType type;

    @PostConstruct
    private void init() {
        if (type == null) {
            type = ProfilePrivilegeType.READ;
        }
    }
}
