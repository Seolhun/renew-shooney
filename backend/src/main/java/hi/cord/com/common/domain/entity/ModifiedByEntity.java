package hi.cord.com.common.domain.entity;

import hi.cord.com.jpa.user.domain.user.User;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;

/**
 * The type Common domain For Entity.
 * <p>
 * AccessType is so important to access from Embedded Class
 *
 * @Field : MODIFIED_BY
 * @Property : modifiedByEntity
 */
@Data
@Access(AccessType.PROPERTY)
@Embeddable
public class ModifiedByEntity {
    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_NICKNAME", length = 60)
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
}
