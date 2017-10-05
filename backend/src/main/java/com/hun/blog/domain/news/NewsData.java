package com.hun.blog.domain.news;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document(collection = "news")
@Data
public class NewsData implements Serializable {
    @Id
    private String id;

    @Indexed(unique = true)
    private long idx;

    private String headerImage;

    private String title;

    private String content;

    private List<String> tags;

    private List<String> images;

    private String fromSource;

    private Date createdDate;

    private String createdBy;

    private Date modifiedDate;

    private String modifiedBy;

    private boolean isDeleted;

    /*
     * Paging Part
    */
    @Transient
    private int pageIndex;

    @Transient
    private int pageSize;
}