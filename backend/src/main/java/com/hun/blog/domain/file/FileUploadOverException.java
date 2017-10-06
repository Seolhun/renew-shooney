package com.hun.blog.domain.file;

import java.io.Serializable;

public class FileUploadOverException extends Exception implements Serializable {

    public FileUploadOverException() {
        super();
    }

    public FileUploadOverException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadOverException(String message) {
        super(message);
    }

    public FileUploadOverException(Throwable cause) {
        super(cause);
    }
}