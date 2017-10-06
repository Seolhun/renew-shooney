package com.hun.blog.domain.nlp;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "nlp_phrase")
@Data
public class NlpPhrase implements Serializable {
    @Id
    private String id;

    private String where;

    private Object phrase;

    private Date createdDate;

    /*
     * Paging Part
    */
    @Transient
    private int pageIndex;

    @Transient
    private int pageSize;

    public NlpPhrase() {

    }

    public NlpPhrase(String where) {
        this.where = where;
    }
}