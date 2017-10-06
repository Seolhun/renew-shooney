package com.hun.blog.domain.comment;

import com.hun.blog.domain.AbstractCommon;
import com.hun.blog.domain.blog.Blog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity(name = "TB_COMMENT")
public class Comment extends AbstractCommon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.DETACH)
    @JoinColumn(foreignKey = @ForeignKey(name = "COMMENT_BLOG_FK"), name = "COMMENT_BLOG_ID", referencedColumnName = "BLOG_ID", nullable = false)
    private Blog blogInComment;

    @Column(name = "COMMENT_CONTENT", length = 300, nullable = false)
    private String content;

    @Column(name = "COMMENT_LIKES")
    private int recommend;

    @Version
    @Column(name = "VERSION")
    private int version;

    /*
     * Paging Part
    */
    @Transient
    private int pageIndex;

    @Transient
    private int pageSize;

}
