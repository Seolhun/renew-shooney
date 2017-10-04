package com.hun.blog.common.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class Paging implements Serializable {
	/** For Paging Field */
	// current Page
	private int pageIndex;
	// Total Page
	private int totalPage;
	// paging limit count
	private int limit;

	// 현재 블록에서 시작 번호
	private int startNumInCurrent;
	// 현재 블록에서 끝 번호
	private int lastNumInCurrent;

	/** For searching Field */
	//To search PK
	private String id;
	//To search Index
	private long idx;
	//To search Type
	private int searchType;
	//To search Text
	private String searchText;
	//To serach Date
	private Date searchDate;

	public Paging() {

	}
}