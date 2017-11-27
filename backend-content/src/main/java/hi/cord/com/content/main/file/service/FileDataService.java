package hi.cord.com.content.main.file.service;

import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.content.main.file.domain.FileData;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

import java.io.IOException;

public interface FileDataService extends CommonRestService<FileData> {
    FileData insertFile(FileData e) throws FileUploadException, IOException;
}
