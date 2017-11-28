package hi.cord.com.content.main.file;

import hi.cord.com.content.main.file.domain.FileData;
import hi.cord.com.content.main.file.service.FileDataService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private FileDataService fileDataService;

    private final String FILE_PATH = "/Users/hunseol/Desktop/file/";

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
    public ResponseEntity fileSave(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        // 파일 가져오기.
        // 1 MB = 1024 * 1024 Bytes || 1 GB = 1024 * 1024 * 1024 Bytes.
        List<MultipartFile> multiFiles = multipartHttpServletRequest.getFiles("boardWithFileList");

        long sumFileSize = 0;
        //각각의 파일 크기 계산
        for (MultipartFile multiFile : multiFiles) {
            sumFileSize += multiFile.getSize();
        }
        // 파일 크기 총합 계산.
        if (sumFileSize > 1024 * 1024 * 30) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File size is over");
        }

        FileData fileData = new FileData(multiFiles);
        try {
            fileDataService.insertFile(fileData);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).body(fileData.toString());
    }
}
