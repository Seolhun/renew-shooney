package com.hun.blog.domain.sequence;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "customSequences")
public class CustomSequences implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2579036721159703259L;

	@Id
	private long id;

	//ex) Client, Survey, Symptom, Brainwave
	private String key;
	
	private String hospitalIdx;

	public CustomSequences() {

	}

	public CustomSequences(long id) {
		this.id = id;
	}

	public CustomSequences(long id, String key) {
		this.id = id;
		this.key = key;
	}

}