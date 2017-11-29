package hi.cord.com.common.domain.error;

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
public class ErrorHandlerException extends RuntimeException {
    private int errorCode;
    private String errorClass;
    private String errorMsg;
    private String errorUri;
    private String clientIp;

    private Date createdDate;
    private String createdBy;

    public ErrorHandlerException() {

    }

    public ErrorHandlerException(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ErrorHandlerException(int httpStatus, String errorMsg) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        this.errorCode = httpStatus;
        this.errorMsg = errorMsg;
        this.errorUri = request.getRequestURI();
        this.clientIp = (String) request.getSession().getAttribute("clientIp");
    }

    public ErrorHandlerException(HttpStatus httpStatus, String errorMsg) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        this.errorCode = httpStatus.value();
        this.errorMsg = errorMsg;
        this.errorUri = request.getRequestURI();
        this.clientIp = (String) request.getSession().getAttribute("clientIp");
    }

    public ErrorHandlerException(HttpStatus status, String errorMsg, String errorClass) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        this.errorCode = status.value();
        this.errorMsg = errorMsg;
        this.errorClass = errorClass;
        this.errorUri = request.getRequestURI();
        this.clientIp = (String) request.getSession().getAttribute("clientIp");
    }

}