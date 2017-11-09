package hi.cord.com.jpa2.content.domain;

import hi.cord.com.common.domain.entity.BaseEntity;
import hi.cord.com.common.domain.entity.CreatedByEntity;
import hi.cord.com.common.domain.entity.ModifiedByEntity;
import hi.cord.com.jpa2.comment.domain.Comment;
import hi.cord.com.jpa2.file.domain.FileData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity(name = "TB_BLOG_CONTENT")
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Table(uniqueConstraints = @UniqueConstraint(name = "UK_CONTENT_IDX_CREATED_BY", columnNames = {"IDX", "CREATED_NICKNAME"}))
public class BlogContent extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "CONTENT_ID", length = 120)
    private String id;

    @Column(name = "IDX")
    private long idx;

    @NotNull(message = "SpamType is requirement")
    @Column(name = "CONTENT_TYPE", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @NotEmpty(message = "title is requirement")
    @Length(min = 5, max = 200, message = "Title is required as min 5 and max 200 length.")
    @Column(name = "TITLE", nullable = false, length = 200)
    private String title;

    //    @OneToMany(mappedBy = "contentInContent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Tag> tags;

    @Lob
    @NotEmpty(message = "BlogContent is requirement")
    @Length(min = 10, message = "BlogContent is required as min 10 length")
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "HITS", length = 10)
    private int hits;

    @Column(name = "LIKES", length = 10)
    private int likes;

    @Column(name = "COMMENT_DEPTH", length = 10)
    private int depth;

    @OneToMany(mappedBy = "blogContentInComment")
    private List<Comment> comments;

    //To Stored Image, Stream
    @OneToMany(mappedBy = "blogContentInFile")
    private List<FileData> files;

    @CreatedBy
    @AssociationOverrides({
            @AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "CREATED_BY", foreignKey = @ForeignKey(name = "USER_ID")))
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


    public BlogContent() {

    }

    @PostConstruct
    public void init() {
        if (this.contentType == null) {
            this.contentType = ContentType.Essay;
        }
    }


    /**
     * @Warning : This Field have some issue I think.
     */
    @PrePersist
    public void autoIdInit() {
        this.setId(UUID.randomUUID().toString());
    }
}
