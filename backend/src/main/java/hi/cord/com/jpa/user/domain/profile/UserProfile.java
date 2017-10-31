package hi.cord.com.jpa.user.domain.profile;

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

    @PostConstruct
    private void init() {
        if (type == null) {
            type = UserProfileType.PLAYER;
        }
    }
}
