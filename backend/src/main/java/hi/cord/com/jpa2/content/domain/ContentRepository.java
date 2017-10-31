package hi.cord.com.jpa2.content.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, String>{
	List<Content> findAllBy(Content content);

	Content findById(String id);

	boolean deleteById(String id);

	Content findByIdx(long idx);

	boolean deleteByIdx(long idx);

	long countBy(Content content);

	long countDistinctBy(Content content);
}
