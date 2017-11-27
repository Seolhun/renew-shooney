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
public class BaseEntity {
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

    @Column(name = "IS_ACTIVE", nullable = false, columnDefinition = "BIT(1) default 1")
    private boolean active;
}
