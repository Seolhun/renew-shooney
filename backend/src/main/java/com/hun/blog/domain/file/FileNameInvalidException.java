package com.hun.blog.domain.file;

import java.io.Serializable;

public class FileNameInvalidException extends Exception implements Serializable{

    public FileNameInvalidException() {
        super();
    }

    public FileNameInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileNameInvalidException(String message) {
        super(message);
    }

    public FileNameInvalidException(Throwable cause) {
        super(cause);
    }
}