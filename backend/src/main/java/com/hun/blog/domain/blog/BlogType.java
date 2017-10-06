package com.hun.blog.domain.blog;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity
@Table(name = "TB_BLOG_TYPE", uniqueConstraints = {@UniqueConstraint(columnNames = "BLOG_TYPE_NAME")})
public class BlogType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BLOG_TYPE_ID")
    private String id;

    @Column(name = "BLOG_TYPE_NAME", length = 150, unique = true)
    private String name;

    @Column(name = "BLOG_TYPE_COUNTS")
    private int counts;

    @Column(name = "BLOG_TYPE_DEPTH")
    private int depth;

    @Version
    @Column(name = "VERSION")
    private int version;

    public BlogType() {

    }

    public BlogType(Long Id) {
        this.id = id;
    }

    public BlogType(String name) {
        this.name = name;
    }

    public BlogType(Long Id, String name) {
        this.id = id;
        this.name = name;
    }
}
