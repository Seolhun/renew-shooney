package com.hun.blog.domain.news;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document(collection = "news")
@Getter
@Setter
public class NewsData implements Serializable {
	@Id
	private long id;

	@Indexed(unique=true)
	private String idx;

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

	private Integer delCheck=0;
}