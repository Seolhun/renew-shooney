package com.hun.blog.domain.notice;

import com.hun.blog.domain.AbstractCommon;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Data
@Entity(name = "TB_NOTICE")
public class Notice extends AbstractCommon implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NOTICE_ID")
	private Long id;

	@Column(name = "NOTICE_URI", length=100, nullable = false)
	private String uri;

	@Column(name = "NOTICE_CONTENT", length=300, nullable = false)
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NOTICE_START_DATE")
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NOTICE_END_DATE")
	private Date endDate;

	/*
 	* Paging Part
	*/
	@Transient
	private int pageIndex;

	@Transient
	private int pageSize;

	public Notice(){

	}

	public Notice(Long id){
		this.id=id;
	}
}
