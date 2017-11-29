package hi.cord.com.common.domain.error;

public class FileSizeOverException extends RuntimeException {
    public FileSizeOverException() {
        super();
    }

    public FileSizeOverException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeOverException(String message) {
        super(message);
    }

    public FileSizeOverException(Throwable cause) {
        super(cause);
    }
}