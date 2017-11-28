package hi.cord.com.content.main.tag.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
	List<Tag> findAllBy(Tag tag);

	Tag findById(long id);

	Tag findByName(String tagName);

	boolean deleteById(long id);

	long countBy(Tag tag);
}
