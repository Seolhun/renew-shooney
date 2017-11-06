package hi.cord.com.jpa2.comment.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hi.cord.com.common.domain.entity.BaseEntity;
import hi.cord.com.common.domain.entity.CreatedByEntity;
import hi.cord.com.common.domain.entity.ModifiedByEntity;
import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.jpa2.content.domain.Content;
import hi.cord.com.jpa2.content.domain.ContentType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "TB_COMMENT")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Table(uniqueConstraints = @UniqueConstraint(name = "UK_COMMENT_IDX_CREATED_BY", columnNames = {"IDX", "CREATED_NICKNAME"}))
public class Comment extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "COMMENT_ID", length = 120)
    private String id;

    @Column(name = "IDX")
    private long idx;

    @Column(name = "CONTENT_TYPE", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "CONTENT_COMMENT_FK"), name = "CONTENT_ID", referencedColumnName = "CONTENT_ID")
    private Content contentInComment;

    @Column(name = "CONTENT", length = 250, nullable = false)
    private String content;

    @Column(name = "LIKES")
    private int likes;

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

    /**
     * Requirement parameter in Entity
     */
    @Transient
    @JsonSerialize
    @JsonDeserialize
    private Pagination<Comment> pagination;

    //------------Entity Filed finished----------------

    /**
     * @Warning : This Field have some issue I think.
     */
    @PrePersist
    public void autoIdInit() {
        this.setId(UUID.randomUUID().toString());
    }
}
