package hi.cord.com.jpa2.comment.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAllBy(Comment comment);

	Comment findById(long id);

	boolean deleteById(long id);

	long countBy(Comment comment);

	long countDistinctBy(Comment comment);
}
