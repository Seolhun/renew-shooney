package com.hun.blog.common.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Setter
@Getter
@ToString
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 3700632243181428214L;

    private int errorCode;
    private String errorClass;
    private String errorMsg;
    private String errorUri;
    private String clientIp;

    private Date createdDate;
    private String createdBy;

    public CustomException() {

    }

    public CustomException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public CustomException(int httpStatus, String errorMsg) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        this.errorCode = httpStatus;
        this.errorMsg = errorMsg;
        this.errorUri = request.getRequestURI();
        this.clientIp = (String) request.getSession().getAttribute("clientIp");
    }

    public CustomException(HttpStatus httpStatus, String errorMsg) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        this.errorCode = httpStatus.value();
        this.errorMsg = errorMsg;
        this.errorUri = request.getRequestURI();
        this.clientIp = (String) request.getSession().getAttribute("clientIp");
    }

    public CustomException(HttpStatus status, String errorMsg, String errorClass) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        this.errorCode = status.value();
        this.errorMsg = errorMsg;
        this.errorClass = errorClass;
        this.errorUri = request.getRequestURI();
        this.clientIp = (String) request.getSession().getAttribute("clientIp");
    }

}