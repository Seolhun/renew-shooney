package hi.cord.com.jpa2.file.domain;

import hi.cord.com.jpa2.content.domain.BlogContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, String>{
	List<FileData> findAllBy(BlogContent blogContent);

	FileData findById(String id);

	FileData findByIdx(long idx);

	boolean deleteById(String id);

	boolean deleteByIdx(long idx);

	long countBy(FileData fileData);

	long countDistinctBy(FileData fileData);
}
