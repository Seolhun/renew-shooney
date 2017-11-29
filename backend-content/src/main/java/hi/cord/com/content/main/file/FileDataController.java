package hi.cord.com.content.main.file;

import hi.cord.com.common.domain.error.FileSizeOverException;
import hi.cord.com.content.main.file.domain.FileData;
import hi.cord.com.content.main.file.service.FileDataService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

/**
 * The type File data controller.
 */
@RestController
@RequestMapping(value = "/file")
public class FileDataController {
    private static final Logger LOG = LoggerFactory.getLogger(FileDataController.class);

    @Value("shun.multipart.maxFileSize")
    private final String MAX_UPLOAD_SIZE = "";
    @Value("spring.http.multipart.location")
    private final String FILE_PATH = "";

    private FileDataService fileDataService;

    @Autowired
    public FileDataController(FileDataService fileDataService) {
        this.fileDataService = fileDataService;
    }

    // private final String FILE_PATH = "/Users/HunSeol/Desktop/file/";

    /**
     * File save response entity.
     *
     * @param multipartHttpServletRequest the multipart
     * @return the response entity
     *
     * @throws IOException the io exception
     */
    @PostMapping(value = "")
    public ResponseEntity fileSave(MultipartHttpServletRequest multipartHttpServletRequest) {
        // 파일 가져오기.
        // 1 MB = 1024 * 1024 Bytes || 1 GB = 1024 * 1024 * 1024 Bytes.
        List<MultipartFile> multiFiles = multipartHttpServletRequest.getFiles("boardWithFileList");

        List<FileData> savedFiles;
        try {
            savedFiles = fileDataService.insertFile(multiFiles);
        } catch (FileUploadException | IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Files Upload is failed");
        } catch (FileSizeOverException f) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Files size is over. You can use maximum by 36 MB");
        }

        if (savedFiles == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found files");
        }

        return ResponseEntity.status(HttpStatus.OK).body(savedFiles);
    }
}
