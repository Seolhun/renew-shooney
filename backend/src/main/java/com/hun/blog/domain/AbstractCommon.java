package com.hun.blog.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class AbstractCommon {

    @Column(name = "CREATED_BY", length = 60)
    private String createdBy;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @Column(name = "UPDATED_BY", length = 60)
    private String updatedBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_DATE", nullable = false)
    private Date updatedDate;

    @Column(name = "DELETED_FLAG", length = 1)
    private boolean isActived;

    @Version
    @Column(name = "VERSION")
    private int version;

    //Repository or service divide
    @Transient
    private int queryType;

    @PrePersist
    protected void onCreate() {
        createdDate = createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }
}