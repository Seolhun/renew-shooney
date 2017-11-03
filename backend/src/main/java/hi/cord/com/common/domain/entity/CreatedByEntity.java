package hi.cord.com.common.domain.entity;

import hi.cord.com.jpa.user.domain.user.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;

/**
 * <h2>The type Common domain For Entity.</h2>
 * <p>
 * AccessType is so important to access from Embedded Class
 * </p>
 *
 * @Field : CREATED_NICKNAME
 * @Property : nickname
 */
@Data
@Access(AccessType.PROPERTY)
@Embeddable
public class CreatedByEntity {
    @CreatedBy
    @Column(name = "CREATED_NICKNAME", length = 60)
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATED_BY", foreignKey = @ForeignKey(name = "USER_ID"))
    private User user;
}
