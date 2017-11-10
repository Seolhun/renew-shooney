package hi.cord.com.user.user.domain.profile;

import hi.cord.com.common.domain.entity.CreatedByEntity;
import hi.cord.com.common.domain.entity.ModifiedByEntity;
import hi.cord.com.user.user.domain.privilege.ProfilePrivilege;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

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
    private Set<ProfilePrivilege> profiles = new HashSet<>();


    @CreatedBy
    @AssociationOverrides({
            @AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "CREATED_BY"))
    })
    @AttributeOverrides({
            @AttributeOverride(name = "user", column = @Column(name = "CREATED_BY")),
            @AttributeOverride(name = "nickname", column = @Column(name = "CREATED_NICKNAME", length = 60))
    })
    @Embedded
    private CreatedByEntity createdByEntity;

    @LastModifiedBy
    @AssociationOverrides({
            @AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "LAST_MODIFIED_BY"))
    })
    @AttributeOverrides({
            @AttributeOverride(name = "user", column = @Column(name = "LAST_MODIFIED_BY")),
            @AttributeOverride(name = "nickname", column = @Column(name = "LAST_MODIFIED_NICKNAME", length = 60))
    })
    @Embedded
    private ModifiedByEntity modifiedByEntity;

    @PostConstruct
    private void init() {
        if (type == null) {
            type = UserProfileType.GUEST;
        }
    }
}
