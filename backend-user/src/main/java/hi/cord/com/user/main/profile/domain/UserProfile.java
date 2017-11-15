package hi.cord.com.user.main.profile.domain;

import hi.cord.com.user.main.privilege.domain.ProfilePrivilege;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The type User profile.
 *
 * @link {UserProfileType}
 */
@Entity(name = "TB_USER_PROFILE")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Getter
@Setter
public class UserProfile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROFILE_ID")
    private long id;

    @Column(name = "PROFILE_TYPE", length = 20, unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserProfileType type;

    // User, How many have Privileges.
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TB_PROFILE_REFER_PRIVILEGE",
            foreignKey = @ForeignKey(name = "FK_PROFILE_ID"),
            joinColumns = {@JoinColumn(name = "PROFILE_ID") },
            inverseForeignKey = @ForeignKey(name = "FK_PRIVILEGE_ID"),
            inverseJoinColumns = {@JoinColumn(name = "PRIVILEGE_ID") })
    private Set<ProfilePrivilege> privileges = new HashSet<>();

    @PostConstruct
    private void init() {
        if (type == null) {
            type = UserProfileType.GUEST;
        }
    }
}
