package com.hun.blog.domain.sequence;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "custom_sequence")
public class CustomSequence implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2579036721159703259L;

    @Id
    private long id;

    //ex) news, ask ... etc (Domain name)
    private String key;

    public CustomSequence() {

    }

    public CustomSequence(long id) {
        this.id = id;
    }

    public CustomSequence(long id, String key) {
        this.id = id;
        this.key = key;
    }

}