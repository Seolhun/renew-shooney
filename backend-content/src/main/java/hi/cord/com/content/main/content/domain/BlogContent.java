package hi.cord.com.content.main.content.domain;

import hi.cord.com.common.domain.entity.BaseCreatedBy;
import hi.cord.com.common.domain.entity.BaseEntity;
import hi.cord.com.common.domain.entity.BaseModifiedBy;
import hi.cord.com.content.main.comment.domain.Comment;
import hi.cord.com.content.main.file.domain.FileData;
import hi.cord.com.content.main.tag.domain.Tag;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity(name = "TB_BLOG_CONTENT")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(uniqueConstraints = @UniqueConstraint(name = "UK_CONTENT_IDX_CREATED_BY", columnNames = {"IDX", "CREATED_BY_NICKNAME"}))
@BatchSize(size = 20)
public class BlogContent extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "CONTENT_ID", length = 120)
    private String id;

    @Column(name = "IDX")
    private Long idx;

    // @NotNull(message = "ContentType is requirement")
    // @Column(name = "CONTENT_TYPE", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    // @NotEmpty(message = "title is requirement")
    // @Length(min = 5, max = 200, message = "Title is required as min 5 and max 200 length.")
    @Column(name = "TITLE", nullable = false, length = 200)
    private String title;

    @Lob
    // @NotEmpty(message = "Content is requirement")
    // @Length(min = 10, message = "BlogContent is required as min 10 length")
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Lob
    // @NotEmpty(message = "Content is requirement")
    // @Length(min = 10, message = "Markdown is required as min 10 length")
    @Column(name = "MARKDOWN_CONTENT", nullable = false)
    private String markdownContent;

    @Column(name = "HITS", length = 10)
    private Integer hits;

    @Column(name = "LIKES", length = 10)
    private Integer likes;

    @Column(name = "COMMENT_DEPTH", length = 10)
    private Integer depth;

    // User, How many have Privileges.
    @BatchSize(size = 20)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "TB_CONTENT_MATCHING_TAG",
            foreignKey = @ForeignKey(name = "FK_CONTENT_TAG_REFER"),
            joinColumns = {@JoinColumn(name = "CONTENT_ID")},
            inverseForeignKey = @ForeignKey(name = "FK_TAG_CONTENT_REFER"),
            inverseJoinColumns = {@JoinColumn(name = "TAG_ID")}
    )
    private Set<Tag> tags;

    @OneToMany(mappedBy = "contentInComment")
    private List<Comment> comments;

    //To Stored Image, Stream
    @OneToMany(mappedBy = "contentInFiles")
    private List<FileData> files;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @CreatedBy
    @AttributeOverrides({
            @AttributeOverride(name = "createdByUserId", column = @Column(name = "CREATED_BY_ID", length = 120)),
            @AttributeOverride(name = "createdByNickname", column = @Column(name = "CREATED_BY_NICKNAME", length = 60))
    })
    private BaseCreatedBy baseCreatedBy;

    @LastModifiedBy
    @AttributeOverrides({
            @AttributeOverride(name = "modifiedByUserId", column = @Column(name = "MODIFIED_BY_ID", length = 120)),
            @AttributeOverride(name = "modifiedByNickname", column = @Column(name = "MODIFIED_BY_NICKNAME", length = 60))
    })
    private BaseModifiedBy baseModifiedBy;

    public BlogContent() {

    }

//    @Override
//    public String toString() {
//        return new com.google.gson.Gson().toJson(this);
//    }
}
