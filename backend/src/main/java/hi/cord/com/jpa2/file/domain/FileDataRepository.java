package hi.cord.com.jpa2.file.domain;

import hi.cord.com.jpa2.file.domain.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileDataRepository extends JpaRepository<FileData, Long>{

	List<FileData> findAllBy(FileData finalData);

	FileData findById(Long id);

	FileData insert(FileData finalData);

	void delete(Long id);

	long countBy(FileData finalData);

	long countDistinctBy(FileData finalData);
}
