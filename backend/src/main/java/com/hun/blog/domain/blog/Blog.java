package com.hun.blog.domain.blog;

import com.hun.blog.domain.AbstractCommon;
import com.hun.blog.domain.comment.Comment;
import com.hun.blog.domain.file.FileData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity
@Table(name = "TB_BLOG")
public class Blog extends AbstractCommon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BLOG_ID")
    private String id;

    @Column(name = "BLOG_IDX")
    private long idx;

    @Column(name = "BLOG_TYPE", length = 30)
    private String blogType;

    @OneToMany(mappedBy = "blogInFile")
    private List<FileData> fileDataList;

    @OneToMany(mappedBy = "blogInComment")
    private List<Comment> commentList;

    @Column(name = "BLOG_TITLE", length = 150, nullable = false)
    private String title;

    @Column(name = "BLOG_CONTENT", length = 300, nullable = false)
    private String content;

    @Column(name = "BLOG_HITS")
    private int hits;

    @Column(name = "BLOG_LIKES")
    private int recommend;

    @Column(name = "BLOG_DEPTH")
    private int depth;

    @Version
    @Column(name = "VERSION")
    private int version;

    @Transient
    private List<MultipartFile> files;

    /*
     * Paging Part
    */
    @Transient
    private int pageIndex;

    @Transient
    private int pageSize;

    public Blog() {

    }

    public Blog(String id) {
        this.id = id;
    }
}
