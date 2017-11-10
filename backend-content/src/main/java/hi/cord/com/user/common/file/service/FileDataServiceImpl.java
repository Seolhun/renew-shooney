package hi.cord.com.user.common.file.service;

import hi.cord.com.user.common.file.domain.FileData;
import hi.cord.com.user.common.file.domain.FileDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("fileDataService")
public class FileDataServiceImpl implements FileDataService {
    private static final Logger LOG = LoggerFactory.getLogger(FileDataServiceImpl.class);

    @Autowired
    protected FileDataRepository fileDataRepository;

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
    public FileData insert(FileData fileData) {
        LOG.debug("p : " + fileData.toString());
        return fileDataRepository.save(fileData);
    }

    @Override
    public FileData updateById(FileData fileData, String accessBy) {
        LOG.debug("p : " + fileData.toString());
        FileData dbFileData = fileDataRepository.findById(fileData.getId());
        LOG.debug("r : " + dbFileData);
        if (dbFileData != null) {
            dbFileData.setFileDataSavedPath(fileData.getFileDataSavedPath());
            dbFileData.setFileDataOriginName(fileData.getFileDataOriginName());
            dbFileData.setFileDataSavedName(fileData.getFileDataSavedName());
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

}
