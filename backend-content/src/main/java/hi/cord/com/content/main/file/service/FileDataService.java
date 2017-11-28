package hi.cord.com.content.main.file.service;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.content.main.content.domain.BlogContent;
import hi.cord.com.content.main.file.domain.FileData;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface FileDataService extends CommonRestService<FileData> {
    FileData insertFile(FileData e) throws FileUploadException, IOException;

    long getIdxByNickname(String nickname);

    Pagination<FileData> findAll(FileData fileData, Pageable pageable);

    FileData findByIdx(long idx, String nickname);
}
