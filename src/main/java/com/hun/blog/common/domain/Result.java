package com.hun.blog.common.domain;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The type Result.
 *
 * @param <T> T is Class to send client.
 */
@Data
public class Result<T> implements Serializable{

	private boolean isRight;
	private int type;

	private T resultObject;
	private List<T> resultList;
	private String content;
	private String message;
	private HttpStatus httpStatus;

	private String createdBy;
	private Date createdDate;
}


