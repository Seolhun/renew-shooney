package hi.cord.com.jpa2.comment.domain;

import hi.cord.com.jpa2.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAllBy(Comment comment);

	Comment findById(long id);

	Comment insert(Comment comment);

	void delete(long id);

	long countBy(Comment comment);

	long countDistinctBy(Comment comment);
}
