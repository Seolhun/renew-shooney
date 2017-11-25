package hi.cord.com.content.main.comment.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hi.cord.com.common.domain.entity.BaseEntity;
import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.content.main.content.domain.BlogContent;
import hi.cord.com.content.main.content.domain.ContentType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

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
    private BlogContent contentInComment;

    @Column(name = "CONTENT", length = 250, nullable = false)
    private String content;

    @Column(name = "LIKES")
    private int likes;

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
