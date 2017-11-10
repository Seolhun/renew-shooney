package hi.cord.com.common.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;


/**
 * The type Common domain For Entity.
 *
 * AccessType is so important to access from Embedded Class
 * @Field : CREATED_BY
 * @Property : createdBy
 */
@Access(AccessType.PROPERTY)
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
@Setter
@Embeddable
public class BaseEntity {
    @CreatedBy
    @Column(name = "CREATED_BY_USER_ID", length = 120)
    private String createdByUserId;

    @LastModifiedBy
    @Column(name = "MODIFIED_BY_USER_ID", length = 120)
    private String modifiedByUserId;

    @CreatedBy
    @Column(name = "CREATED_BY_NICKNAME", length = 60)
    private String createdByNickname;

    @LastModifiedBy
    @Column(name = "MODIFIED_BY_NICKNAME", length = 60)
    private String modifiedByNickname;

    @CreatedDate
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @LastModifiedDate
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "IS_ACTIVE", nullable=false, columnDefinition = "BIT(1) default 1")
    private boolean active;
}
