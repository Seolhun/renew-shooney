package hi.cord.com.jpa.user.domain.profile;

import hi.cord.com.common.domain.entity.CreatedByEntity;
import hi.cord.com.common.domain.entity.ModifiedByEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;

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
    private static final long serialVersionUID = 3788477180129427170L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROFILE_ID")
    private long id;

    @Column(name = "PROFILE_TYPE", length = 10, unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserProfileType type;

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
            type = UserProfileType.PLAYER;
        }
    }
}
