package hi.cord.com.jpa2.board.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
	List<Board> findAllBy(Board board);

	Board findById(long id);

	boolean deleteById(long id);

	long countBy(Board board);

	long countDistinctBy(Board board);
}
