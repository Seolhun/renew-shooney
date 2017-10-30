package hi.cord.com.jpa2.file.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, Long>{

	List<FileData> findAllBy(FileData finalData);

	FileData findById(Long id);

	FileData insert(FileData finalData);

	void delete(Long id);

	long countBy(FileData finalData);

	long countDistinctBy(FileData finalData);
}
