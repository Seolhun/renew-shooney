package hi.cord.com.common.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * The type Common domain For Entity.
 * <p>
 * AccessType is so important to access from Embedded Class
 *
 * @Field : CREATED_BY
 * @Property : createdBy
 */
@Access(AccessType.PROPERTY)
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
@Setter
@Embeddable
public class BaseCreatedBy {
    @CreatedBy
    @Column(name = "CREATED_BY_USER_ID", length = 120)
    private String createdByUserId;

    @CreatedBy
    @Column(name = "CREATED_BY_NICKNAME", length = 60)
    private String createdByNickname;
}
