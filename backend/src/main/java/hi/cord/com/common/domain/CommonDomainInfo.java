package hi.cord.com.common.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
public class CommonDomainInfo {
    @Column(name = "CREATED_BY", length = 60)
    private String createdBy;

    @Column(name = "MODIFIED_BY", length = 60)
    private String modifiedBy;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "IS_ACTIVE", nullable=false, columnDefinition = "tinyint(1) default 1")
    private boolean isActive;

    @Version
    @Column(name = "VERSION")
    private long version;
}
