package hi.cord.com.content.main.file.service;

import hi.cord.com.content.main.file.domain.FileData;
import hi.cord.com.content.main.file.domain.FileDataRepository;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service("fileDataService")
public class FileDataServiceImpl implements FileDataService {
    private static final Logger LOG = LoggerFactory.getLogger(FileDataServiceImpl.class);

    @Value("spring.http.multipart.max-request-size")
    private final long MAX_UPLOAD_SIZE = 0;
    @Value("spring.http.multipart.location")
    private final String FILE_PATH = "";

    private final FileDataRepository fileDataRepository;

    @Autowired
    public FileDataServiceImpl(FileDataRepository fileDataRepository) {
        this.fileDataRepository = fileDataRepository;
    }

    @Override
    public List<FileData> findByList() {
        return null;
    }

    @Override
    public Page<FileData> findByPage(FileData fileData, Pageable pageable) {
        return null;
    }

    @Override
    public FileData findById(String id) {
        FileData fileData = fileDataRepository.findById(id);
        return fileData;
    }

    @Override
    public FileData findById(long id) {
        return null;
    }

    @Override
    public FileData findByIdx(long idx) {
        return null;
    }


    @Override
    public FileData insert(FileData fileData) throws FileUploadException, IOException {
        //유효성 검사.
        List<FileData> fileDataList = new ArrayList<>();

        //MultipartFile에 index 0은 빈값이 온다.(알아보고 처리해야함. 그래서 1로 시작)
        long totalFileSize = 0;
        FileData tempFile;
        List<MultipartFile> files = fileData.getMultipartFiles();
        for (MultipartFile file : files) {

            if (!file.isEmpty()) {
                //파일이름으로 확장자명과 파일이름 나누기.
                String originName = file.getOriginalFilename();
                String onlyFileExtension = originName.substring(originName.lastIndexOf("."));
                String savedName = UUID.randomUUID().toString() + onlyFileExtension;

                String fileDataType = file.getContentType();

                tempFile = new FileData();
                tempFile.setOriginName(originName);
                tempFile.setSavedName(savedName);
//                fileData.setSavedPath(FILE_PATH);
                tempFile.setFileType(fileDataType);
                tempFile.setSize(file.getSize());
                totalFileSize += file.getSize();
//                 fileData.setCreatedByNickname();

                fileDataList.add(tempFile);
            }
        }

        //파일 최대 요청용량 초과시 에러처리.
        if (totalFileSize > MAX_UPLOAD_SIZE) {
            throw new FileUploadException();
        }

        for (FileData file : fileDataList) {
            //폴더 없을시 폴더 만들기.
            File directory = new File(FILE_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File serverFile = new File(directory.getAbsolutePath() + File.separator + file.getSavedName());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            byte[] bytes = file.getBytes();
            stream.write(bytes);
            stream.close();

            //게시판을 인서트한 후 파일을 인서트한다.
            fileDataRepository.save(file);
        }
        return fileData;
    }

    @Override
    public FileData updateById(FileData fileData, String accessBy) {
        LOG.debug("p : " + fileData.toString());
        FileData dbFileData = fileDataRepository.findById(fileData.getId());
        LOG.debug("r : " + dbFileData);
        if (dbFileData != null) {
            dbFileData.setSavedPath(fileData.getSavedPath());
            dbFileData.setOriginName(fileData.getOriginName());
            dbFileData.setSavedName(fileData.getSavedName());
        }
        return dbFileData;
    }


    @Override
    public boolean deleteById(String id, String accessBy) {
        FileData dbFileData = fileDataRepository.findById(id);
        if (dbFileData != null) {
            fileDataRepository.delete(dbFileData.getId());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(long id, String accessBy) {
        return false;
    }

    @Override
    public boolean deleteByIdx(long idx, String accessBy) {
        FileData dbFileData = fileDataRepository.findByIdx(idx);
        if (dbFileData != null) {
            fileDataRepository.delete(dbFileData.getId());
            return true;
        }
        return false;
    }

    @Override
    public long count(FileData fileData) {
        return fileDataRepository.countBy(fileData);
    }


    /**
     * File name validation boolean.
     * Have Error : False
     * OK : True
     *
     * @param fileName the file name
     * @return the boolean
     */
    public boolean fileNameValidation(String fileName) {
        if (fileName.split(".").length > 2) {
            return false;
        }

        return true;
    }

}
