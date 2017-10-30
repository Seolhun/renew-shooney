package hi.cord.com.jpa2.file;

import hi.cord.com.jpa2.file.domain.FileData;
import hi.cord.com.jpa2.file.service.FileDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type File data controller.
 */
@RestController
@RequestMapping(value = "/file")
public class FileDataController {
	private static final Logger LOG = LoggerFactory.getLogger(FileDataController.class);

	private FileDataService fileDataService;
	private BCryptPasswordEncoder bCrypt;

	private final String FILE_PATH = "/Users/hunseol/Desktop/file/";

	@Autowired
	public FileDataController(FileDataService fileDataService, BCryptPasswordEncoder bCrypt) {
		this.fileDataService = fileDataService;
		this.bCrypt = bCrypt;
	}

	// private final String FILE_PATH = "/Users/HunSeol/Desktop/file/";

	/**
	 * File save response entity.
	 *
	 * @param multipart the multipart
	 * @return the response entity
	 *
	 * @throws IOException the io exception
	 */
	@PostMapping(value = "")
	public ResponseEntity fileSave(MultipartHttpServletRequest multipart) throws IOException {
		// 파일 가져오기.
		// 1 MB = 1024 * 1024 Bytes || 1 GB = 1024 * 1024 * 1024 Bytes.
		List<MultipartFile> multiFiles = multipart.getFiles("boardWithFileList");
		LOG.info("param : " + multiFiles.toString());
		int sum = 0;

		//각각의 파일 크기 계산 
		for (MultipartFile multiFile : multiFiles) {
			if (multiFile.getSize() > 1024 * 1024 * 30) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File size is over");
			}
			sum += multiFile.getSize();
		}
		// 파일 크기 총합 계산.
		if (sum > 1024 * 1024 * 30) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File size is over");
		}

		// 파일 저장
		List<FileData> fileDataList = new ArrayList<>();
		for (MultipartFile multiFile : multiFiles) {
			FileData fileData = new FileData();

			//확장자명 추출, 2개시 에러처리.
			String orginalFileName = multiFile.getOriginalFilename();
			String splitName[] = orginalFileName.split(".");
			if (splitName.length > 2) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is invalid");
			}

			//파일 OriginalName과 확장자명 저장..
			fileData.setFileDataOriginName(splitName[0]);
			fileData.setFileDataType(splitName[1]);

			String contentType = multiFile.getContentType();
			LOG.info("param : {}", contentType);
			// fileData.setFileDataType(contentType);

			//파일이름 Bcrypt로 암호화.
			String encryptFileName = bCrypt.encode(orginalFileName);
			String savedPath = FILE_PATH + encryptFileName;

			fileData.setFileDataSavedName(encryptFileName);
			fileData.setFileDataSavedPath(savedPath);

			// File Upload
			File uploadFile = new File(savedPath);
			try {
				multiFile.transferTo(uploadFile);
			} catch (IllegalStateException e) {
				LOG.debug("File Catch IllegalStateException");
				e.printStackTrace();
			} catch (IOException e) {
				LOG.debug("File Catch IOException");
				e.printStackTrace();
			}
			// 이상 없을시 파일리스트에 담는다.
			fileDataList.add(fileData);
		}

		// FileDB저장
		for (FileData file:fileDataList) {
			fileDataService.insert(file);
		}
		return ResponseEntity.status(HttpStatus.OK).body("Files is Inserted");
	}
}
