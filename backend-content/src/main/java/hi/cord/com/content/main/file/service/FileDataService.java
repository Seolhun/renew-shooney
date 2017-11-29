package hi.cord.com.content.main.file.service;

import hi.cord.com.common.domain.pagination.Pagination;
import hi.cord.com.common.service.rest.CommonRestService;
import hi.cord.com.content.main.file.domain.FileData;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileDataService extends CommonRestService<FileData> {
    List<FileData> insertFile(List<MultipartFile> multipartFiles) throws FileUploadException, IOException;

    long getIdxByNickname(String nickname);

    Pagination<FileData> findAll(FileData fileData, Pageable pageable);

    FileData findByIdx(long idx, String nickname);
}
