package hi.cord.com.jpa2.file.service;

import hi.cord.com.jpa2.file.domain.FileData;
import hi.cord.com.jpa2.file.domain.FileDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service("fileDataService")
public class FileDataServiceImpl implements FileDataService {
	private static final Logger LOG = LoggerFactory.getLogger(FileDataServiceImpl.class);
	
	@Autowired
	protected FileDataRepository fileDataRepository;

	@Override
	public FileData update(FileData fileData) {
		LOG.info("param : "+fileData.toString());
		FileData dbFileData = fileDataRepository.findById(fileData.getId());
		LOG.info("return : "+dbFileData);
		if (dbFileData != null) {
			dbFileData.setFileDataSavedPath(fileData.getFileDataSavedPath());
			dbFileData.setFileDataOriginName(fileData.getFileDataOriginName());
			dbFileData.setFileDataSavedName(fileData.getFileDataSavedName());
		}
		return dbFileData;
	}

	@Override
	public long count(FileData fileData) {
		return fileDataRepository.countBy(fileData);
	}


	@Override
	public FileData insert(FileData fileData) {
		LOG.info("param : "+fileData.toString());
		return fileDataRepository.insert(fileData);
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
		return null;
	}

	@Override
	public FileData findById(long id) {
		FileData fileData= fileDataRepository.findById(id);
		return fileData;

	}

	@Override
	public boolean deleteById(String id) {
		return false;
	}

	@Override
	public boolean deleteById(long id) {
		FileData dbFileData= fileDataRepository.findById(id);
		if (dbFileData != null) {
			fileDataRepository.delete(dbFileData.getId());
			return true;
		}
		return false;
	}
}
