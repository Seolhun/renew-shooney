package hi.cord.com.jpa2.content.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hi.cord.com.common.domain.entity.BaseEntity;
import hi.cord.com.common.domain.entity.CreatedByEntity;
import hi.cord.com.common.domain.entity.ModifiedByEntity;
import hi.cord.com.common.domain.enumtypes.ContentType;
import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.jpa2.comment.domain.Comment;
import hi.cord.com.jpa2.file.domain.FileData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity(name = "TB_CONTENT")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"IDX", "CREATED_NICKNAME"}))
public class Content extends BaseEntity implements Serializable {
    @Id
    @Column(name = "CONTENT_ID", length = 120)
    private String id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    private long idx;

    @NotEmpty
    @Column(name = "TITLE", nullable = false, length = 200)
    private String title;

    @Lob
    @NotEmpty
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "HITS", length = 10)
    private int hits;

    @Column(name = "LIKES", length = 10)
    private int likes;

    @Column(name = "CONTENT_TYPE", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Column(name = "COMMENT_DEPTH", length = 10)
    private int depth;

    @OneToMany(mappedBy = "contentInContent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(mappedBy = "contentInFile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FileData> files;

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

    @Version
    @Column(name = "VERSION")
    private long version;

    @Transient
    @JsonSerialize
    @JsonDeserialize
    private Pagination<Content> pagination;

    @PostConstruct
    private void init() {
        if (contentType == null) {
            contentType = ContentType.Essay;
        }
    }

    /**
     * @Warning : This Field have some issue I think.
    * */
    @PrePersist
    public void autoIdInit() {
        this.setId(UUID.randomUUID().toString());
    }
}
