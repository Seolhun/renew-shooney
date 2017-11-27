package hi.cord.com.content.main.file.service;

import hi.cord.com.common.service.rest.CommonBatchRestService;
import hi.cord.com.content.main.file.domain.FileData;
import hi.cord.com.content.main.file.domain.FileDataRepository;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * The type File data service.
 */
@Service("fileDataService")
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
public class FileDataServiceImpl implements FileDataService, CommonBatchRestService<FileData> {
    private static final Logger LOG = LoggerFactory.getLogger(FileDataServiceImpl.class);

    @Value("shun.multipart.maxFileSize")
    private final String MAX_UPLOAD_SIZE = "";
    @Value("spring.http.multipart.location")
    private final String FILE_PATH = "";

    private final SessionFactory sessionFactory;
    private final FileDataRepository fileDataRepository;

    @Autowired
    public FileDataServiceImpl(SessionFactory sessionFactory, FileDataRepository fileDataRepository) {
        this.sessionFactory = sessionFactory;
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
        return fileDataRepository.findById(id);
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
    public FileData insert(FileData fileData) {
        return fileData;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED)
    public FileData insertFile(FileData fileData) throws FileUploadException, IOException {
        //유효성 검사.
        List<FileData> storeFileList = new ArrayList<>();

        //MultipartFile에 index 0은 빈값이 온다.(알아보고 처리해야함. 그래서 1로 시작)
        long totalFileSize = 0;
        List<MultipartFile> files = fileData.getMultipartFiles();
        if (files.size() < 1) {
            return fileData;
        }

        for (MultipartFile multipartFile : files) {
            if (!multipartFile.isEmpty()) {
                FileData tempFile = this.setMultipartIntoFileData(multipartFile);
                totalFileSize += multipartFile.getSize();
                storeFileList.add(tempFile);
            }
        }

        // Check Max File Size
        if (totalFileSize > Long.parseLong(MAX_UPLOAD_SIZE)) {
            throw new FileUploadException();
        }

        // Stored File Into Server
        for (FileData file : storeFileList) {
            this.storedFileIntoServer(file);
        }

        //After Stored File and Saved File Info into DataBase
        fileDataRepository.save(storeFileList);
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


    /****** Batch Service Part *********
     * Batch Service Part Start
     *********************************/
    @Override
    public List<FileData> insertBatchByList(List<FileData> list) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        for (int i = 0; i < list.size(); i++) {
            FileData fileData = new FileData();
            session.save(fileData);
            if (i % 20 == 0) {
                session.flush();
                session.clear();
            }
        }
        tx.commit();
        session.close();
        return list;
    }
    /****** Batch Service Part End *******/


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

    /**
     * Get from Multipart file // Set Into FileData Entity.
     */
    private FileData setMultipartIntoFileData(MultipartFile multipartFile) throws IOException {
        //Get File information from Multipart file
        String originName = multipartFile.getOriginalFilename();
        String onlyFileExtension = originName.substring(originName.lastIndexOf("."));
        String savedName = UUID.randomUUID().toString() + onlyFileExtension;
        String fileDataType = multipartFile.getContentType();

        //Set Information into entity
        FileData fileData = new FileData();
        fileData.setBytes(multipartFile.getBytes());
        fileData.setMultipartFile(multipartFile);
        fileData.setSize(multipartFile.getSize());

        fileData.setOriginName(originName);
        fileData.setSavedName(savedName);
        // fileData.setSavedPath(FILE_PATH);
        fileData.setFileType(fileDataType);
        // fileData.setCreatedByNickname();
        return fileData;
    }

    /**
     * Stored File into Server Method
     */
    private void storedFileIntoServer(FileData file) throws IOException {
        //폴더 없을시 폴더 만들기.
        File directory = new File(FILE_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        MultipartFile multipartFile = file.getMultipartFile();
        File serverFile = new File(directory.getAbsolutePath() + File.separator + file.getSavedName());
        multipartFile.transferTo(serverFile);
        // BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
        // byte[] bytes = file.getBytes();
        // stream.write(bytes);
        // stream.close();
    }
}
